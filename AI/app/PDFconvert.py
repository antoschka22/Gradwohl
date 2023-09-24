import os
from os.path import isfile, join
import PyPDF2
import re
import csv
import pandas as pd
from datetime import datetime


def fnc_print_products():
    try:
        for product in products:
            print(product)
    except:
        pass


# delete the bestellungen column
def fnc_delete_bestellungen_column():
    try:
        for product in products:
            try:
                del product[3]
            except:
                if len(product) == 1:
                    products.remove(product)
    except:
        pass


# printProducts()
# get the names of the products
def fnc_get_product_names():
    try:
        product_names = []
        for product in products:
            name = product[0]

            # Find the index of the first text character
            first_text_index = 0
            for i in range(len(name)):
                if name[i].isalpha():
                    first_text_index = i
                    break

            # Remove any digits before the first text character
            name = name[first_text_index:]
            for i in range(len(name)):
                if name[i].isalpha():
                    break
                else:
                    name = name[i + 1:]
            product_names.append(name)
        return product_names

    except:
        pass


# print(getProductNames())
# get the amount orderer of a product
def fnc_get_frische_bestellung(product_name):
    for product in products:
        if product_name in product[0]:
            return product[1]


# getFrischeBestellung('VK Bio D-Brot o.H')
def fnc_days_diff(date_str):
    date_obj = datetime.strptime(date_str, '%Y_%m_%d')
    base_date = datetime(2023, 2, 13)
    delta = date_obj - base_date
    return delta.days


# print(days_diff('2023_02_18')+1)
def fnc_insert_into_csv(products, file_input):
    fieldnames = ['product_name', 'frisch', 'teigig', 'file']
    # TODO Rewrite this to be more efficient, it literally checks all data, until the element has been found, it really only needs to check the file field
    # check if file is in csv:
    try:
        # looks ugly, but is as efficient as it gets in python
        file_local = open('products.csv', 'r')
        if str(file_input) in str(file_local):
            file_local.close()
            print("file already in csv")
            return
        file_local.close()
    except:
        print("file does not exist")
        csvfile = open('products.csv', 'a+', newline='')
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        csvfile.close()
        # csvfile = open('products.csv', 'a+', newline='')
        # writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        # writer.writeheader()
        # csvfile.close()

    with open('products.csv', 'a+', newline='') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        for i in products:
            # debug, shows a dataset + the file input
            # print(str(i) + file_input)
            # the only real error we can get here are out of bounds errors
            input_file_str = file_input.replace("_", "").strip(".pdf")
            try:
                # select individual data points
                writer.writerow({'product_name': i[0], 'frisch': i[1], 'teigig': i[2], 'file': input_file_str})
            except:
                try:
                    print("error in writing data in file: {}, name: {}, skipping".format(input_file_str,i[0]))
                    continue
                except:
                    print("error in writing data in file: {}, low data integrity, skipping".format(input_file_str))
                    continue
        csvfile.close()


def fnc_convert_pdf():
    parts = []

    # delete the header in the first page
    # add text to an array
    def fnc_visitor_body(text, tm, font_dict, font_size, cm):
        y = tm[5]
        if py2_reader.get_page_number(page) == 0 and y < 750:
            parts.append(text)
        elif py2_reader.get_page_number(page) > 0:
            parts.append(text)

    page.extract_text(visitor_text=fnc_visitor_body)
    text_body = "".join(parts).split(" ")

    text_body = list(filter(lambda x: x != "", text_body))

    # print(text_body)

    #################################

    # remove the \n and organize the array
    for i in range(len(text_body)):
        index_of_new_line = text_body[i].find('\n')
        if index_of_new_line != -1:
            # Check if there is a digit or letter before the \n character
            if any(char.isalnum() for char in text_body[i][:index_of_new_line]):
                next_index = i + 1
                text_body.insert(next_index, text_body[i][index_of_new_line + 1:])
                text_body[i] = text_body[i][:index_of_new_line]
            elif i != 0:  # Check if this is not the first index
                text_body[i] = text_body[i][:index_of_new_line]
    for i in range(len(text_body)):
        index_of_new_line = text_body[i].find('\n')
        if index_of_new_line != -1:
            # Check if there is a digit or letter before the \n character
            if i == 0:
                text_body[0] = text_body[0].replace('\n', '')
            elif any(char.isalnum() for char in text_body[i][:index_of_new_line]):
                next_index = i + 1
                text_body.insert(next_index, text_body[i][index_of_new_line + 1:])
                text_body[i] = text_body[i][:index_of_new_line]
            else:
                text_body[i] = text_body[i][:index_of_new_line]

    # remove the footer
    text_body = text_body[:-4]

    # Remove bug in pdf
    try:
        if '2152io' in text_body:
            index_to_replace = text_body.index('2152io')
            text_body[index_to_replace] = 'VK Bio'
        elif '2152' in text_body:
            index_to_replace = text_body.index('2152')
            text_body[index_to_replace] = 'VK Bio'
    except:
        pass

    # remove bug with numbers and capitalized text
    for i, text in enumerate(text_body):
        match = re.search(r'(\d+)([a-zA-Z]+)(?!\s)', text)
        if match:
            number = match.group(1)
            word = match.group(2)
            # Test
            # text_body[i] = text.replace(number + word, number)
            text_body[i] = text.replace(number, number)
            if i < len(text_body) - 1:
                text_body[i + 1] = text_body[i + 1].strip()

    products = []
    i = 0

    # delete bug with product
    try:
        index = text_body.index("Thunfischsalat") + 1
        text_body.pop(index)
    except:
        pass
    # print(text_body)

    # add in two dimensional array the product with the amount
    while i < len(text_body):
        if text_body[i].isdigit() and 60 <= int(text_body[i]) <= 5000:
            j = i + 1
            temp = text_body[i] + " "
            while j < len(text_body) and not text_body[j].isdigit():
                temp += text_body[j] + " "
                j += 1
            inner_arr = [temp.rstrip()]  # The first element of inner_arr is the concatenated string
            while j < len(text_body) and len(inner_arr) < 4:
                if text_body[j].isdigit() and 60 <= int(text_body[j]) <= 5000:
                    break
                else:
                    inner_arr.append(text_body[j])
                    j += 1
            products.append(inner_arr)
            i = j - 1  # Skip over the indices that were added to two_d_array
        i += 1

    # Terrible fix to remove any ALPHA chars from ID
    i = 0  # I hate that i is a global var...
    while i < len(products):
        products[i][0] = products[i][0][0:[c for c, x in enumerate(products[i][0]) if x.isalpha()][0]]
        products[i][0] = ''.join(c for c in products[i][0] if c.isdigit())
        i += 1

    return products

    # !!!bug with Stangenbrote
    # print(products)


global date
global py2_reader
global page
global products

filepath = "./bestellungen"
# make sure not to read in directories
onlyfiles = [f for f in os.listdir(filepath) if isfile(join(filepath, f)) and f.lower().endswith('.pdf')]
onlyfiles.sort()
print(onlyfiles)
for file_current in onlyfiles:
    # empty out products for every file
    all_products = []
    py2_reader = PyPDF2.PdfReader(filepath + "/" + file_current)
    try:
        # read all pages from pdf, (we just assume less than 100 pages)
        for i_page_num in range(100):
            page = py2_reader.pages[i_page_num]
            products = fnc_convert_pdf()
            all_products += products
            # convert PDF problem
            fnc_delete_bestellungen_column()
            fnc_delete_bestellungen_column()
    except Exception as e:
        e
    print("insert " + file_current)
    fnc_insert_into_csv(all_products, file_current)