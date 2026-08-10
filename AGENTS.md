# Conway's Game of Life

## Business Requirements

- An MVP of a Conway's Game of Life simulation as a web app
- https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
- 
1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
2. Any live cell with two or three live neighbours lives on to the next generation.
3. Any live cell with more than three live neighbours dies, as if by overpopulation.
4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
5. Write a readme with instructions on how to run the application, including any prerequisites and dependencies
6. allow the user to choose the initial state from a set of predefined patterns from the wiki page


## Technical Details

- Implemented as a modern javaFx application
- No persistence
- Allow user to start, stop, and reset the simulation
- Allow user to set the initial state of the grid by clicking on cells to toggle them between alive and dead
- Allow user to adjust the speed of the simulation
- Keep track of the number of generations that have passed and display it to the user


## Strategy

1. Write plan with success criteria for each phase to be checked off. Include project scaffolding, including .gitignore, and rigorous unit testing.
2. Execute the plan ensuring all critiera are met
3. Carry out extensive integration testing fixing defects
4. Only complete when the MVP is finished and tested, with the server running and ready for the user

## Coding standards

1. Use latest versions of libraries and idiomatic approaches as of today
2. Keep it simple - NEVER over-engineer, ALWAYS simplify, NO unnecessary defensive programming. No extra features - focus on simplicity.
3. Be concise. Keep README minimal. IMPORTANT: no emojis ever
4. Use Java 21
5. Use JavaFX for the GUI
6. Use JUnit 5 for unit testing
7. Use Maven for dependency management and build
8. Use GitHub Actions for CI/CD
9. Use GitHub Issues for project management
10. document the code with Javadoc comments, including class-level and method-level documentation
11. Always use meaningful variable and method names
12. Adhere to the SOLID principles of object-oriented design
13. Prefer composition over inheritance where appropriate
14. Prefer immutability where appropriate
15. Keep code simple, do not overcomplicate with unnecessary abstractions
16. try use functional programming features of Java 21 where appropriate, but do not overuse them
17. Make UI look professional, but do not overcomplicate it with unnecessary features or animations
