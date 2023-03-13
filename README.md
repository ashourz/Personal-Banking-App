# My Personal Banking App
Personal banking application to view accounts and account details hosted on cloud.

# Project Assignment
- Build an app where you can see the list of your banking and card accounts (Accounts View). Please use the attached base project to add your code.
- The list on Accounts View is vertically scrollable. When user touch one of the items, the app should take them to next page (Detail View) where users can see the details.
- The user can touch either the physical Done button or the back arrow at the top left corner on Detail View to go back to the Accounts View.
- For each screen, display the Name on Toolbar.
- On the Accounts View, you should make a network call to fetch the data from the JSON file hosted on AWS. The application should read the Json data and display in order.
- For each item in the data, display the following items:
  - Display the Bank or Card image depending on the account type.
  - Display Account name
  - Display Account Description.
  - Display transfer type which is "ACH-Same Day" for Bank Account and "Instant" for a Card.
  - Display an arrow on the right side.
  
# Key Libraries
- Kotlin
  - Coroutines
  - Flow
- XML View UI
- Material Design
- Jetpack Navigation 
  - NavGraph
  - TypeSafeArgs
  - Databinding
- Jetpack Lifecycle with LiveData
- Jetpack Room SQLite Database
- GSON
- Retrofit2
- OkHttp

# Key Features
- MVVM Applicaiton Architecture
- Rest API client pulls account infomration from AWS
- API response mapping from JSON to Kotlin data class object
- Mutex object wrapper ensures single operation to local database at a time

# Screenshots
<p float="left">
  <img src="https://user-images.githubusercontent.com/39238415/224742460-19b2e456-c1cd-4c27-9042-e0b48022b58c.png" width="225" />
  <img src="https://user-images.githubusercontent.com/39238415/224742494-1e28ba79-7b43-4580-b30a-117791080db0.png" width="225" /> 
  <img src="https://user-images.githubusercontent.com/39238415/224742525-857679d6-85e9-4443-9b88-ceacf21eeeed.png" width="225" />
  <img src="https://user-images.githubusercontent.com/39238415/224742552-f078824e-4bd4-4a74-b139-7a1d57f28812.png" width="225" />
</p>
