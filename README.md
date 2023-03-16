# Dewashish-APIwiz-Assignment

The assignment is as follows:

Get all the currencies values from any of the open currency API for the past 30 days.
Store/update those values in your local DB either sql/mongo . (Mongo is much preferred).
Expose an end point which returns the data predicting the specific currency value on the given date. (See example for more info)
Also expose another endpoint, say, I give X amount of USD, what’s the Y amount of INR, I’ll be getting. Support conversion only for G10 currencies. Convert based on the most recent currency value.
Example :- 
You have data of INR values like this 79,80,82,81,82.5,84.3,85.6,81.7,80,80
These are sample INR currency values from the past 10 days, and I want to predict INR value on some dd-mm-yyyy date , based on these values I'll predict 81.7 as dd-mm-yyyy day’s INR’s value.
Like this you need to store past 30 days in your db and retrieve data whenever the predict end point is being triggered and show the predicted currency value based on past 30days INR value.
Sample curl for conversion end point 
			curl --location --request GET 'http://localhost:8000/currency/exchange’ \

			--header ‘base: 2 USD’ \

			--header ‘destination: INR’

Sample response 
			{

				“2USD”:”164.59 INR”

			}

Sample curl for predict end point
			curl --location --request GET 'http://localhost:8000/currency/predict’ \

			--header ‘baseCurrency: INR’ \

			--header ‘date: 23-03-2023’

Sample response 
	{

		“predictedValue”:”81.7 INR”

	}
