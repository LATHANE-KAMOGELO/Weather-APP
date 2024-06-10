# Weather-APP

LATHANE KAMOGELO


ST10273510


The purpose of the Weather App is to allow users to record and monitor the daily temperatures and weather conditions for a week. The app aims to help users keep track of weather patterns and easily view detailed information for each day.


Explanation:
The Weather App features a splash screen, a main screen for inputting weather data, and a detailed view screen for reviewing the recorded data. The app allows users to enter the morning and afternoon temperatures, as well as a brief note on the weather conditions, for each day of the week. It calculates the average temperature over the week and provides a detailed view of the data entered. Users can navigate between the main screen and the detailed view screen and have the option to clear the entered data.



Pseudocode

START

MAIN ACTIVITY
  ON CREATE
    SET CONTENT TO WeatherTheme
      CALL WeatherApp()

FUNCTION WeatherApp
  DECLARE showSplashScreen AS BOOLEAN INITIALIZED TO TRUE
  DECLARE showNavigationDialog AS BOOLEAN INITIALIZED TO FALSE

  IF showSplashScreen IS TRUE
    CALL SplashScreen
      SET showSplashScreen TO FALSE
      SET showNavigationDialog TO TRUE
  ELSE
    CALL MainContent

  IF showNavigationDialog IS TRUE
    CALL NavigationDialog
      ON NAVIGATE TO MAIN SCREEN SET showNavigationDialog TO FALSE
      ON EXIT APP SET showNavigationDialog TO FALSE

FUNCTION SplashScreen(onTimeout)
  LAUNCH EFFECT
    WAIT FOR 7000 MILLISECONDS
    CALL onTimeout

  DISPLAY SPLASH SCREEN WITH LOGO AND DETAILS

FUNCTION MainContent
  DECLARE navController AS NAVIGATION CONTROLLER
  CREATE NAV HOST
    ADD MAIN SCREEN ROUTE
    ADD DETAILED VIEW SCREEN ROUTE

DATA CLASS WeatherData
  PROPERTIES:
    day: STRING
    morningTemp: STRING
    afternoonTemp: STRING
    weatherCondition: STRING

FUNCTION MainScreen(navController)
  DECLARE weatherData AS LIST OF WeatherData WITH 7 DAYS INITIALIZED TO DAYS MONDAY TO SUNDAY
  DECLARE listState AS LAZY LIST STATE

  FUNCTION calculateAverageTemp
    DECLARE totalTemp AS DOUBLE INITIALIZED TO 0
    DECLARE count AS INTEGER INITIALIZED TO 0
    FOR EACH record IN weatherData
      CONVERT record.morningTemp TO DOUBLE OR 0 AND ADD TO totalTemp
      INCREMENT count IF morningTemp > 0
      CONVERT record.afternoonTemp TO DOUBLE OR 0 AND ADD TO totalTemp
      INCREMENT count IF afternoonTemp > 0
    RETURN totalTemp DIVIDED BY count IF count > 0 ELSE 0

  DISPLAY LIST OF weatherData
    FOR EACH record IN weatherData
      DISPLAY TextField FOR morningTemp
      DISPLAY TextField FOR afternoonTemp
      DISPLAY TextField FOR weatherCondition

  DISPLAY AVERAGE TEMPERATURE

  DISPLAY Button FOR CLEARING DATA
    SET weatherData TO EMPTY LIST OF WeatherData WITH 7 DAYS

  DISPLAY Button FOR NAVIGATING TO DETAILED VIEW
    NAVIGATE TO "detailed"

FUNCTION DetailedViewScreen(navController)
  DECLARE weatherData AS LIST OF WeatherData WITH 7 DAYS INITIALIZED TO DAYS MONDAY TO SUNDAY

  DISPLAY LIST OF weatherData
    FOR EACH record IN weatherData
      DISPLAY day
      DISPLAY morningTemp
      DISPLAY afternoonTemp
      DISPLAY weatherCondition

  DISPLAY Button FOR NAVIGATING BACK TO MAIN SCREEN
    NAVIGATE TO "main"

FUNCTION NavigationDialog(onNavigateToMainScreen, onExitApp)
  DISPLAY ALERT DIALOG
    DISPLAY TEXT "Do you want to navigate to the main screen or exit the app?"
    DISPLAY Button FOR NAVIGATING TO MAIN SCREEN
      CALL onNavigateToMainScreen
    DISPLAY Button FOR EXITING APP
      CALL onExitApp

ENDPseudocode

plaintext
START

MAIN ACTIVITY
  ON CREATE
    SET CONTENT TO WeatherTheme
      CALL WeatherApp()

FUNCTION WeatherApp
  DECLARE showSplashScreen AS BOOLEAN INITIALIZED TO TRUE
  DECLARE showNavigationDialog AS BOOLEAN INITIALIZED TO FALSE

  IF showSplashScreen IS TRUE
    CALL SplashScreen
      SET showSplashScreen TO FALSE
      SET showNavigationDialog TO TRUE
  ELSE
    CALL MainContent

  IF showNavigationDialog IS TRUE
    CALL NavigationDialog
      ON NAVIGATE TO MAIN SCREEN SET showNavigationDialog TO FALSE
      ON EXIT APP SET showNavigationDialog TO FALSE

FUNCTION SplashScreen(onTimeout)
  LAUNCH EFFECT
    WAIT FOR 7000 MILLISECONDS
    CALL onTimeout

  DISPLAY SPLASH SCREEN WITH LOGO AND DETAILS

FUNCTION MainContent
  DECLARE navController AS NAVIGATION CONTROLLER
  CREATE NAV HOST
    ADD MAIN SCREEN ROUTE
    ADD DETAILED VIEW SCREEN ROUTE

DATA CLASS WeatherData
  PROPERTIES:
    day: STRING
    morningTemp: STRING
    afternoonTemp: STRING
    weatherCondition: STRING

FUNCTION MainScreen(navController)
  DECLARE weatherData AS LIST OF WeatherData WITH 7 DAYS INITIALIZED TO DAYS MONDAY TO SUNDAY
  DECLARE listState AS LAZY LIST STATE

  FUNCTION calculateAverageTemp
    DECLARE totalTemp AS DOUBLE INITIALIZED TO 0
    DECLARE count AS INTEGER INITIALIZED TO 0
    FOR EACH record IN weatherData
      CONVERT record.morningTemp TO DOUBLE OR 0 AND ADD TO totalTemp
      INCREMENT count IF morningTemp > 0
      CONVERT record.afternoonTemp TO DOUBLE OR 0 AND ADD TO totalTemp
      INCREMENT count IF afternoonTemp > 0
    RETURN totalTemp DIVIDED BY count IF count > 0 ELSE 0

  DISPLAY LIST OF weatherData
    FOR EACH record IN weatherData
      DISPLAY TextField FOR morningTemp
      DISPLAY TextField FOR afternoonTemp
      DISPLAY TextField FOR weatherCondition

  DISPLAY AVERAGE TEMPERATURE

  DISPLAY Button FOR CLEARING DATA
    SET weatherData TO EMPTY LIST OF WeatherData WITH 7 DAYS

  DISPLAY Button FOR NAVIGATING TO DETAILED VIEW
    NAVIGATE TO "detailed"

FUNCTION DetailedViewScreen(navController)
  DECLARE weatherData AS LIST OF WeatherData WITH 7 DAYS INITIALIZED TO DAYS MONDAY TO SUNDAY

  DISPLAY LIST OF weatherData
    FOR EACH record IN weatherData
      DISPLAY day
      DISPLAY morningTemp
      DISPLAY afternoonTemp
      DISPLAY weatherCondition

  DISPLAY Button FOR NAVIGATING BACK TO MAIN SCREEN
    NAVIGATE TO "main"

FUNCTION NavigationDialog(onNavigateToMainScreen, onExitApp)
  DISPLAY ALERT DIALOG
    DISPLAY TEXT "Do you want to navigate to the main screen or exit the app?"
    DISPLAY Button FOR NAVIGATING TO MAIN SCREEN
      CALL onNavigateToMainScreen
    DISPLAY Button FOR EXITING APP
      CALL onExitApp

ENDPseudocode

plaintext
START

MAIN ACTIVITY
  ON CREATE
    SET CONTENT TO WeatherTheme
      CALL WeatherApp()

FUNCTION WeatherApp
  DECLARE showSplashScreen AS BOOLEAN INITIALIZED TO TRUE
  DECLARE showNavigationDialog AS BOOLEAN INITIALIZED TO FALSE

  IF showSplashScreen IS TRUE
    CALL SplashScreen
      SET showSplashScreen TO FALSE
      SET showNavigationDialog TO TRUE
  ELSE
    CALL MainContent

  IF showNavigationDialog IS TRUE
    CALL NavigationDialog
      ON NAVIGATE TO MAIN SCREEN SET showNavigationDialog TO FALSE
      ON EXIT APP SET showNavigationDialog TO FALSE

FUNCTION SplashScreen(onTimeout)
  LAUNCH EFFECT
    WAIT FOR 7000 MILLISECONDS
    CALL onTimeout

  DISPLAY SPLASH SCREEN WITH LOGO AND DETAILS

FUNCTION MainContent
  DECLARE navController AS NAVIGATION CONTROLLER
  CREATE NAV HOST
    ADD MAIN SCREEN ROUTE
    ADD DETAILED VIEW SCREEN ROUTE

DATA CLASS WeatherData
  PROPERTIES:
    day: STRING
    morningTemp: STRING
    afternoonTemp: STRING
    weatherCondition: STRING

FUNCTION MainScreen(navController)
  DECLARE weatherData AS LIST OF WeatherData WITH 7 DAYS INITIALIZED TO DAYS MONDAY TO SUNDAY
  DECLARE listState AS LAZY LIST STATE

  FUNCTION calculateAverageTemp
    DECLARE totalTemp AS DOUBLE INITIALIZED TO 0
    DECLARE count AS INTEGER INITIALIZED TO 0
    FOR EACH record IN weatherData
      CONVERT record.morningTemp TO DOUBLE OR 0 AND ADD TO totalTemp
      INCREMENT count IF morningTemp > 0
      CONVERT record.afternoonTemp TO DOUBLE OR 0 AND ADD TO totalTemp
      INCREMENT count IF afternoonTemp > 0
    RETURN totalTemp DIVIDED BY count IF count > 0 ELSE 0

  DISPLAY LIST OF weatherData
    FOR EACH record IN weatherData
      DISPLAY TextField FOR morningTemp
      DISPLAY TextField FOR afternoonTemp
      DISPLAY TextField FOR weatherCondition

  DISPLAY AVERAGE TEMPERATURE

  DISPLAY Button FOR CLEARING DATA
    SET weatherData TO EMPTY LIST OF WeatherData WITH 7 DAYS

  DISPLAY Button FOR NAVIGATING TO DETAILED VIEW
    NAVIGATE TO "detailed"

FUNCTION DetailedViewScreen(navController)
  DECLARE weatherData AS LIST OF WeatherData WITH 7 DAYS INITIALIZED TO DAYS MONDAY TO SUNDAY

  DISPLAY LIST OF weatherData
    FOR EACH record IN weatherData
      DISPLAY day
      DISPLAY morningTemp
      DISPLAY afternoonTemp
      DISPLAY weatherCondition

  DISPLAY Button FOR NAVIGATING BACK TO MAIN SCREEN
    NAVIGATE TO "main"

FUNCTION NavigationDialog(onNavigateToMainScreen, onExitApp)
  DISPLAY ALERT DIALOG
    DISPLAY TEXT "Do you want to navigate to the main screen or exit the app?"
    DISPLAY Button FOR NAVIGATING TO MAIN SCREEN
      CALL onNavigateToMainScreen
    DISPLAY Button FOR EXITING APP
      CALL onExitApp

END


SCREENSHOTS
![HMA1_Lab1_2024 - lab-a6119850-9ac5-485b-91e4-ae86562bf8c8 northeurope cloudapp azure com_7014 - Remote Desktop Connection 2024_06_10 10_28_18](https://github.com/LATHANE-KAMOGELO/Weather-APP/assets/166804549/76b742d1-2fa3-4cd9-9176-3f8d0a679e79)
![HMA1_Lab1_2024 - lab-a6119850-9ac5-485b-91e4-ae86562bf8c8 northeurope cloudapp azure com_7014 - Remote Desktop Connection 2024_06_10 10_28_38](https://github.com/LATHANE-KAMOGELO/Weather-APP/assets/166804549/5a1905b2-785f-4cff-a78f-8b2d80fca400)
![HMA1_Lab1_2024 - lab-a6119850-9ac5-485b-91e4-ae86562bf8c8 northeurope cloudapp azure com_7014 - Remote Desktop Connection 2024_06_10 10_28_33](https://github.com/LATHANE-KAMOGELO/Weather-APP/assets/166804549/536940de-e938-4792-82a8-675dc9564432)
![HMA1_Lab1_2024 - lab-a6119850-9ac5-485b-91e4-ae86562bf8c8 northeurope cloudapp azure com_7014 - Remote Desktop Connection 2024_06_10 10_28_24](https://github.com/LATHANE-KAMOGELO/Weather-APP/assets/166804549/fa2ab903-8607-47ca-ac3c-f2ebd2cd7eac)


POWERPOINT PRESENTATION
[IMAD25112-EXAM.pptx](https://github.com/user-attachments/files/15765055/IMAD25112-EXAM.pptx)



