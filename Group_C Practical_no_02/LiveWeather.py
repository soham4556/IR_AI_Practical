import requests

# OpenWeatherMap API Key
api_key = '6e6f9659fef62e5c5d1103979100d281'
# Base URL for the API
base_url = 'http://api.openweathermap.org/data/2.5/weather'

# Input: City/State name
city = input('Enter a city/state name: ')

# Construct the complete API URL
request_url = f"{base_url}?appid={api_key}&q={city}"

# Send the request to the OpenWeatherMap API
response = requests.get(request_url)

# Check if the request was successful
if response.status_code == 200:
    # Parse the response data in JSON format
    data = response.json()
    
    # Extract weather description
    weather_description = data['weather'][0]['description']
    # Extract temperature (convert from Kelvin to Celsius)
    temperature = round(data['main']['temp'] - 273.15, 2)
    # Extract wind speed
    wind_speed = data['wind']['speed']
    # Extract general weather info
    weather_main = data['weather'][0]['main']
    
    # Output the weather information
    print(f"City: {city}")
    print(f"Temperature: {temperature} °C")
    print(f"Wind Speed: {wind_speed} m/s")
    print(f"Description: {weather_description}")
    print(f"Weather: {weather_main}")
    

else:
    # Error message if request failed
    print("An error occurred while retrieving the weather data.")
   
       
    