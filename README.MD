# General information of the project:  
  
- **Language**: Kotlin  
- **IDE**: Android Studio  
- **Design Pattern**: MVVM (Model - View - ViewModel) with LiveData  
- **DI**: Dagger Hilt  
- **Concurrency**:  Coroutines  
    
The project follows a feature structure, there is a feature folder where all additional features would be placed, in this example, the feature provided is `Puzzle`  
  
Each feature manages the three layers of clean architecture (Data - Domain - UI).  
  
As DI framework I used Dagger Hilt, its configuration is done in the subfolder DI  
  
The management of the asynchronous work is done with `Coroutines`
    
The project uses the `UseCases` pattern in order to help identify the different use cases the app could have  
  
I decided to use the Android Navigation Component in order to show the fragment, and help the scalability if an other fragment is added.

Please note I didn't have time for adding Unit and UI tests. For adding them I would use the following:

- Unit Test: I would use the spec framework as I think it improves the readability of the different use cases and as well the scalability for more complex input data structures. I would test the view model and the use case, checking the viewstate is the one expected.
- UI test: I would use esspresso and I would test that the visual output for the different view states is the one expected, and I would implement end to end test in order to check the different behaviours of iterations with the screen.
     
# Scrum:  
In order to manage this project I followed the principles of _Scrum Methodology_ identifying the following tasks and estimating them as follows:  
  
- [x] **TaskA**: Setup the project -> Story Points: **2**  
- [x] **TaskB**: Establish the MVVM architecture with Data-Domain-UI -> Story Points: **5**  
- [x]  **TaskC**: Implement Dagger-> Story Points: **5**  
- [x] **TaskD**: Implement algorithm for solving the puzzle-> Story Points: **3**  
- [x] **TaskE**: UI implementation of the of the fragment -> Story Points: **3**  
  
Other tasks identified not implemented:  
- [ ] **TaskF**: Unit tests -> Story Points: **2**  
- [ ] **TaskG**: UITest of the view -> Story Points: **3** 
- [ ] **TaskH**: Improve designs  
- [ ] **TaskI**: Define custom styles for the app  
- [ ] **TaskJ**:  Migrate gradle to KTS  
