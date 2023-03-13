import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_csv('products.csv')


def gradient_descent(m_now, b_now, points, L):
    m_gradient = 0
    b_gradient = 0

    n = len(points)

    for i in range(n):
        x = float(points.iloc[i].date)
        y = points.iloc[i].VK_D_Vanillekrapfen

        m_gradient += -(2 / n) * x * (y - (m_now * x + b_now))
        b_gradient += -(2 / n) * (y - (b_now * x + b_now))

    m = m_now - m_gradient * L
    b = b_now - b_gradient * L

    return m, b


m = 0
b = 0
L = 0.0001
epochs = 500

for i in range(epochs):
    m, b = gradient_descent(m, b, data, L)

print(m, b)

plt.scatter(data.date, data.VK_D_Vanillekrapfen, color="black")
plt.plot(list(range(3, 80)), [m * x + b for x in range(3, 80)], color="red")