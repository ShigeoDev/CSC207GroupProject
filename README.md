# Project: Recipe Search Aid
### Members
Nigel Loh (Shigeodev)

Brad Hyunsoo Cho (BradC1)

Kevin Hu (kevinhu01)

Jingduo Zhou (Jingduozhou)

Rui Hu (XYPKQ896)

# Summary of Project:
The project we decided on, is a recipe based application that includes features that provide the user with information
regarding recipes. Our Recipe Search Application addresses a common daily challenge: deciding what to cook. As university 
students, we recognized the need for a tool that simplifies meal planning and recipe discovery.
This application utilizes the EDAMAM Recipe API, giving access to a vast database of recipes. Users can then
search recipes based on various criteria, view nutritional information, save favourite recipes, and plan meal plans.
By streamlining these features into one interface, we have created a practical solution that helps users make quick, 
informed decisions about their meals. Our development was driven by a few key user stories we came up with near the 
beginning of our project. We also created an initial view to help guide us in our development.

### User Stories
(Kevin) A person wants to create an account that keeps track of recipes they save. This account has a username and 
password, and an empty list for saved recipes.

(Brad) A person wants to check the number of calories a certain recipe contains. He then runs the program to get the number 
of calories given the name of the recipe.

(Jingduo) A person who gets cold recently wants to eat meal with more vitamin C. He checks the app to search for which 
food has high vitamin C to help him get better quickly.

(Rui) A person wants to search the recipes for salads. They run the program and enter salads. Then, the output is all 
the recipes of salads.

(Nigel) A user wants to store recipes so that they can access it later.

(Group) User wants to figure out what to eat for three meals in a day. User can click a button to generate a meal plan for that day. 

### Image of Project View
This is our initial Project View. Without any coding, we decided on what we wanted our application to look like, as well
as the methods we wanted to implement.
![CSC207 Group-2](https://github.com/user-attachments/assets/7626397b-22e4-42a5-9662-6788ead723c5)
# Table of Contents:
- [Summary](#summary-of-project)
- [Features of the Software](#features-of-the-software)
- [Installation Instructions](#installation-instructions)
- [Usage Guide](#usage-guide)
- [License](#license)
- [Section for Feedback](#section-for-feedback)
- [Section for Contributors](#section-for-contributions)

# Features of the Software:
### Login and Signup:
Users can create an account with storage of their information (username, password, recipes list).
Once registered, users can log in to access their account and are automatically directed to a home screen.
### Homepage:
The Homepage serves as the main navigation hub where users can access all core features of the application. After logging in, users can select from multiple options:

- View Saved Recipes
- Access Meal Planner
- Search by Dish Type
- Get Recipe Calories
- Nutrition Filter

Each button directs users to their desired feature while maintaining their current session.
### Get Calories Use Case:
The Get Calories feature allows users to search up the desired recipe and view its calorie content as well as the ability
to save recipes.
Simply enter a recipe name, and the application will display the total calories along with basic recipe 
information. Perfect for users tracking their caloric intake or planning meals with specific nutritional goals.
### Meal Plan Use Case:
Meal plan feature helps the User create a meal plan for the day, including breakfast, lunch, and dinner. Random recipes are 
generated for each meal.
### Store Recipe Use Case:
Once logged in, users begin browsing various parts of the application, they can click a save button beside each recipe 
that they come across to save it to their user. Once a user saves a recipe, they can access it through the "Saved Recipe" button on the homepage.
### Search by Dish Type Use Case:
After clicking the search by dish type button in the homepage. Users can select the desired dish types in the 
dish type page. By clicking the search button, all the recipes under the specified dish types will be shown.
### Nutrition Filter Use Case:
The nutrition filter feature allows users to select the desired nutritions and then be presented with recipes that are high 
in the selected nutritions.
# Installation Instructions:
1. Navigate to our Group GitHub Repository and clone the repository
2. Open up your desired software and pull the code
3. Make sure to set your environment variables to the API Key and ID (EDAMAM API)
4. Find main.java under src/main/java/app/main
5. Run Main
# Usage Guide
1. To use the app, first run Main
2. You will be prompted with a SignUp screen. If you don't have an account, put in your details to signup for an account.
If you already have an account, click the LogIn button which will navigate you to the LogIn screen. Here you can put in your 
details to log in.

<img width="780" alt="Screenshot 2024-11-30 at 4 05 35 PM" src="https://github.com/user-attachments/assets/c8edeede-fbda-47da-ad48-5283156f121c">
<img width="803" alt="Screenshot 2024-11-30 at 4 07 30 PM" src="https://github.com/user-attachments/assets/3ff95eea-1626-4af7-926a-55f68965b156">

3. Once you log in, you will be prompted with a screen that includes the features that our application provides. Decide 
which feature you want to use and click on the correct button.
<img width="569" alt="Screenshot 2024-11-30 at 4 08 26 PM" src="https://github.com/user-attachments/assets/f686ee24-9681-477d-82cf-e961f558237d">

   4. Mealplan: Requires no extra User input.
   5. Saved: Requires no extra User input.
   6. Search by Dish Type: You will be prompted with a screen that includes numerous different dish types. Select your 
   desired dish types and click search.

      <img width="577" alt="Screenshot 2024-11-30 at 4 08 56 PM" src="https://github.com/user-attachments/assets/c511958f-30b5-4875-a2c9-e05ac27cc141">

   7. Get Calories: You will be prompted with a text field. Type in your desired recipe and click submit.
      
      <img width="564" alt="Screenshot 2024-11-30 at 4 10 00 PM" src="https://github.com/user-attachments/assets/bab1cdcb-ac83-47bd-952d-ef868fb6559c">
   8. Once you are presented with the results view, you can then save a  recipe for later use.
    
       <img width="374" alt="Screenshot 2024-11-30 at 4 13 34 PM" src="https://github.com/user-attachments/assets/b14d1113-aa07-4556-9f46-9f2ef0cf31fd">


# License
#### This project uses the Creative Commons Zero v1.0 Universal license.
# Section for Feedback
To submit feedback for our project, create a new issue on our GitHub repository.
Use a clear and descriptive title and describe the improvement you are suggesting.
The improvement must be recognized by all of our members before we start the implementation.
We will review all new issues within a few days. If we disagree with the suggestion, we will 
close the issue. 

# Section for Contributions
Our project is currently closed for outside contributions.






