# -*- coding: utf-8 -*-
"""
Created on Thu Apr 10 16:16:40 2025

@author: itisyijy
"""

# Load libraries
# You must not import any additional libraries other than the ones listed below.
from sklearn.datasets import fetch_openml
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from scipy.stats import t, f, probplot


# Load dataset
ames = fetch_openml(name="house_prices", as_frame=True)
# Keep only numeric columns
X = ames.data.select_dtypes(np.number)
X = X.set_index('Id')
y = ames.target/1000 # target, house sales prices
y.index = X.index

# - 01 ----------------------------
# Make histogram
plt.hist(y, bins=100)
plt.show()


# Make Boxplot
plt.boxplot(y)
plt.show()

# - 02 ----------------------------
# Calcuate Q1, Q2, Q3, and IQR
target = pd.Series(y)
q1 = np.quantile(target, 0.25)
q2 = np.quantile(target, 0.50)
q3 = np.quantile(target, 0.75)
iqr = q3 - q1

# Print Q1, Q2, Q3, and IQR
print(f'Q1: {q1}')
print(f'Q2: {q2}')
print(f'Q3: {q3}')
print(f'IQR: {iqr}')

# - 03 ----------------------------
# Calculate fence
lower_inner_fence = q1 - (1.5 * iqr)
upper_inner_fence = q3 + (1.5 * iqr)

# Filter outliers
outliers = y[(y < lower_inner_fence) | (y > upper_inner_fence)]
y = y[(y >= lower_inner_fence) & (y <= upper_inner_fence)]

# Print Ids of outliers
print("Id of outliers: ")
for id in outliers.index:
    print(f'{id} ', end="")

# - 04 ----------------------------
# Find columns including null value
null_including = X.columns[X.isnull().any()]

# Print name of columns including null value
print("Variables with null values: ", end="")
for name in null_including:
    print(f'{name} ', end="")

# - 05 ----------------------------
# Remove outliers
X1 = X.drop(outliers.index)

# Eliminae explantory variables with null values
X1 = X1.drop(null_including, axis=1)

# Print number of samples and variables in X1
print(f'Number of Samples: {X1.shape[0]}')
print(f'Number of Variables: {X1.shape[1]}')


# - 06 ----------------------------
# Define function for calculating VIF
def calculate_vif(X):
    X = X.copy()
    vif_dict = {}
    
    for i in range(X.shape[1]):
        target = X.iloc[:, i]
        others = X.drop(X.columns[i], axis=1)
        
        regression = LinearRegression()
        regression.fit(others, target)
        r2 = regression.score(others, target)
        
        if r2 < 1:
            vif = 1 / (1 - r2)
        else:
            vif = np.inf
        vif_dict[X.columns[i]] = vif
        
    return vif_dict

# Print each VIF values
vif_result = calculate_vif(X1)
print("VIF Values For Each Variables")
for key, value in vif_result.items():
    print(f'{key}: {value}')

# - 07 ----------------------------

# - 08 ----------------------------
# Remove VIF above 10
X2 = X1
for key, value in vif_result.items():
    if (value == np.inf):
        X2 = X2.drop(columns=key, axis=1)

# Print each VIF values of X2
vif_result = calculate_vif(X2)
print("VIF Values For Each Variables")
for key, value in vif_result.items():
    print(f'{key}: {value}')

# - 09 ----------------------------
def pearson(X, y):
    X = np.array(X)
    y = np.array(y)
    X_mean = X.mean()
    y_mean = y.mean()
    
    numerator = np.sum((X - X_mean) * (y - y_mean))
    denominator = np.sqrt(np.sum((X - X_mean)**2)) * np.sqrt(np.sum((y - y_mean)**2))
    return numerator / denominator

correlations = {}
for column in X2.columns:
    r = pearson(X2[column], y)
    correlations[column] = abs(r)
    
sorted_correlations = sorted(correlations.items(), key=lambda x: x[1], reverse=True)
top5 = sorted_correlations[:5]

#
print("Top 5 correlation")
for key, value in top5:
    plt.title(f'{key} vs SalePrice\nCorrelation: {value:.4f}')
    plt.scatter(X2[key], y)
    plt.show()

# - 10 ----------------------------

# 1. 상수항 추가
X = X2.copy()
X.insert(0, 'Intercept', 1)

# 2. 행렬 정의
X_mat = X.values
y_mat = y.values.reshape(-1, 1)

n, p = X_mat.shape

# 3. 계수 추정
beta_hat = np.linalg.inv(X_mat.T @ X_mat) @ (X_mat.T @ y_mat)

# 4. 예측 및 잔차
y_pred = X_mat @ beta_hat
residuals = y_mat - y_pred

# 5. 오차 분산
sigma_squared = (residuals.T @ residuals) / (n - p)

# 6. 표준 오차
XtX_inv = np.linalg.inv(X_mat.T @ X_mat)
se = np.sqrt(np.diag(sigma_squared * XtX_inv)).reshape(-1, 1)

# 7. t-value
t_values = beta_hat / se

# 8. p-value (two-tailed)
p_values = 2 * (1 - t.cdf(np.abs(t_values), df=n - p))

# 9. 결과 정리
results = pd.DataFrame({
    'Coefficient': beta_hat.flatten(),
    'Std_Error': se.flatten(),
    't_value': t_values.flatten(),
    'p_value': p_values.flatten()
}, index=X.columns)

print(results)

















# - 11 ----------------------------
# - 12 ----------------------------
# - 13 ----------------------------
# - 14 ----------------------------
# - 15 ----------------------------
# - 16 ----------------------------
# - 17 ----------------------------
# - 18 ----------------------------
# - 19 ----------------------------
# - 20 ----------------------------
# - 21 ----------------------------
# - 22 ----------------------------
# - 23 ----------------------------