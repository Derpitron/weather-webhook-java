# Discord Weather Webhook - Java Flavor
This is a program which pulls current weather from OpenWeatherMap and if it is raining, then it sends a message to a specified Discord webhook. 

# Unique features of this program?
This program allows you to change the interval of the program, change variables, and it sends a start message with the name of the host and local time, as well as any caught exceptions to a debug webhook you specify. It also has a feature where it does not send the message until and unless the weather changes from not-raining to raining. (this part took me much longer than i'd like to figure out.)

# Installation
Clone the repository, modify the placeholder values in `.env`. Then, run
```
mvn compile
```
in the root of the project directory. This will generate a `.class` file, located in `target/classes/me/derpitron`. From there, you can run the class file however you want. 

# Variables
In .env change the placeholders to: your main Discord webhook link, OpenWeatherMap api link (note: this program was built with the OpenWeatherMap Current Weather API in mind so dont use any other API. also use latitude and longitude of your location), debug webhook link to send start and error messages to, and your timezone in Time Zone Database format.

# Change interval of program
If you want to change the interval of the program just change the interval in .env. If your OpenWeatherMap account is using the free plan, make sure to not exceed 60 calls per minute.

# How to use it?
Compile the .java file into a .jar or class file once you've made the necessary changes.  You can run this both locally or on a service like Heroku, but make sure that whatever service you use won't rate limit you and you have enough hours in it to keep it running for as long as you like. Heroku gives free users 450 hours per month, but check with the service you're using!

Star this repo, and follow me and Vishard-006 for more cool projects like these
