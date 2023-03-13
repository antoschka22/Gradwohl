import PyPDF2
import re
import array
import csv
import pandas as pd
from datetime import datetime, timedelta
import os


def printProducts():
    try:
        for product in products:
            print(product)
    except:
        pass


# delete the bestellungen column
def deleteBestellungenColumn():
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
def getProductNames():
    try:
        productNames = []
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
            productNames.append(name)
        return productNames

    except:
        pass


# print(getProductNames())


# get the amount orderer of a product
def getFrischeBestellung(product_name):
    for product in products:
        if product_name in product[0]:
            return product[1]

# getFrischeBestellung('VK Bio D-Brot o.H')


def days_diff(date_str):
    date_obj = datetime.strptime(date_str, '%Y_%m_%d')
    base_date = datetime(2023, 2, 13)
    delta = date_obj - base_date
    return delta.days


# print(days_diff('2023_02_18')+1)

def insertIntoCSV(productNames, bestellungen, date):
    for product in productNames:
        checkNameInCSV(product)

    df = pd.read_csv('products.csv')

    new_row = {'date': days_diff(date) + 1}

    for index, bestellung in enumerate(bestellungen):
        new_row.update({productNames[index]: bestellung[1]})

    new_row_df = pd.DataFrame([new_row])

    df = pd.concat([df, new_row_df], ignore_index=True)

    df.to_csv('products.csv', index=False)

    # put the names of the first order in the csv file


def initializeCSV():
    products = getProductNames()
    products.insert(0, 'date')

    with open('products.csv', 'a', newline='') as file:
        writer = csv.writer(file)

        writer.writerow(products)


def checkNameInCSV(name):
    with open('products.csv', 'r', newline='') as file:
        reader = csv.reader(file)

        data = list(reader)

# checkNameInCSV('Test')
# checkNameInCSV('VK Bio D-Fladenbrot')

def convertPDF():
    parts = []

    # delete the header in the first page
    # add text to an array
    def visitor_body(text, cm, tm, fontDict, fontSize):
        y = tm[5]
        if pageNum == 0 and y < 750:
            parts.append(text)
        elif pageNum > 0:
            parts.append(text)

    page.extract_text(visitor_text=visitor_body)
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
            text_body[i] = text.replace(number + word, number)
            if i < len(text_body) - 1:
                text_body[i + 1] = text_body[i + 1].strip() + word

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
    return products

    # !!!bug with Stangenbrote
    # print(products)


date = '2023_02_24'
file_path = '~/AI/Gradwohl/AI/bestellungen/2023_02_24.pdf'
print(os.path.isfile(file_path))

reader = PyPDF2.PdfReader('~/AI/Gradwohl/AI/bestellungen/{}.pdf'.format(date))
try:
    for i in range(100):
        pageNum = i
        page = reader.pages[pageNum]
        products = convertPDF()
        deleteBestellungenColumn()
        deleteBestellungenColumn()

        # Initialize the CSV if not done yet
        with open('products.csv', 'r', newline='') as file:
            reader = csv.reader(file)

            data = list(reader)

            if len(data) < 1:
                initializeCSV()
            else:
                pass

        print(products)
        insertIntoCSV(getProductNames(), products, date)
except:
    pass


# pageNum = 3
# page = reader.pages[pageNum]


df = pd.read_csv('products.csv')
df.date

df.head()

# PROBLEME: Datum zusammen und name mit underline