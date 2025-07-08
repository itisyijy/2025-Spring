# Slide 1
Hi everyone, we’re Team 8 — Seungjun Kim, Jungyoon Lee, and Hyunmin Hwang.  
Thanks for being here. Today, we’ll be sharing a real-world case study on how data mining can be used in healthcare.

# Slide 2
We’ll start by introducing the problem we looked into, and then go over two case studies that show how data mining techniques were applied to that problem.

# Slide 4
Our topic is the connection between obesity and mental health.  
It’s a complicated issue — there are so many factors involved like stress, sleep, diet, exercise, and even socioeconomic status.  
All these things interact in ways that are hard to capture with simple statistics.

With modern lifestyles — especially in cities — people are becoming less physically active and eating less healthy.  
That’s leading to higher obesity rates and more mental health issues around the world.  
And during the COVID-19 pandemic, things got worse.  
Isolation, stress, and depression were strongly linked to unhealthy behaviors, like gaining weight or drinking more alcohol.

Since all of these factors are connected in complex, non-linear ways, we need more powerful tools to analyze them —  
and that’s where data mining comes in.  
It helps us find hidden patterns and predict who might be at risk.

This kind of insight can actually help in real life — like in designing better public health policies, insurance planning, or personalized healthcare.

Now, let’s move on to the second real-world case we looked at.

# Slide 19
This case is about predicting obesity in working adults in Malaysia.

# Slide 20
The researchers used survey data from Malaysia’s Healthiest Workplace in 2019.  
After cleaning the data — removing people with invalid BMI, pregnancies, or missing values —  
they ended up with over 16,000 participants and 165 features.

About 42% of those in the training set were overweight or obese.  
They split the data into 70% training and 30% testing.  
Most of the features were converted into binary variables using one-hot encoding.

# Slide 21  
To compare prediction performance, they tested four models:  
Three machine learning models — XGBoost, Random Forest, and SVM —  
and one traditional method, Logistic Regression.

Let’s quickly go through how each model works.

# Slide 22  
XGBoost, or eXtreme Gradient Boosting, builds decision trees one at a time —  
and each tree tries to fix the mistakes made by the previous one.  
It’s known for being both fast and accurate, and it has built-in features to reduce overfitting.

# Slide 23  
Random Forest creates many decision trees using different random samples of the data.  
Then it combines their results — kind of like a vote — to make the final prediction.  
This makes the model more stable and helps prevent overfitting.

# Slide 24  
SVM, or Support Vector Machine, finds the best boundary between classes by maximizing the margin between them.  
It focuses on the most important data points — the ones close to the boundary — and can handle non-linear patterns using something called kernel functions.
