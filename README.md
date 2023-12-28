# Secure Banking App with Text File
A simple command-line interface (CLI) banking application implemented in Java, showcasing fundamental concepts for beginners. The application allows users to create accounts, login, perform banking operations such as deposits and withdrawals, check balances, and store user data in a text file.

# Features
Account Creation: 
Users can create a new account by providing a username, password, and initial deposit amount.

Login: 
Existing users can log in with their username and password.

Banking Operations:
Deposit: Users can deposit a specified amount into their account.
Withdraw: Users can withdraw a specified amount from their account.
Check Balance: Users can check their account balance.
Persistent Storage: User account data is stored in a text file, allowing for data persistence between program executions.

# Java Concepts and Features Utilized
1. Object-Oriented Programming (OOP)
Classes and Objects: The project is organized using object-oriented principles, with classes such as SecureBankingAppWithTextFile and Account.
2. File Handling
File I/O: The application uses Java's File, Scanner, and PrintWriter classes to read from and write to the text file (accounts.txt).
3. User Input Handling
Scanner Class: Input from the user is obtained using the Scanner class, a common practice for handling user input in Java.
4. Data Structures
Map (HashMap): The accounts variable is a HashMap used to store user account data efficiently.
5. Exception Handling
FileNotFoundException: The project demonstrates handling potential file-related exceptions using a try-catch block.
6. Control Flow
Loops and Conditional Statements: while loops are used for the program's main flow, and switch-case statements manage user choices.
7. ANSI Escape Codes (for CLI Formatting)
Console Output Formatting: ANSI escape codes for colors and styles enhance the visual appeal of the command-line interface.

# Getting Started
Prerequisites:
Java Development Kit (JDK) installed on your machine.
Running the Application

Clone the repository:
git clone https://github.com/Vedant6800/securebankingAppWithTextFile/

Navigate to the project directory:
cd SecureBankingApp

Compile the Java files:
javac SecureBankingAppWithTextFile.java

Run the application:
java SecureBankingAppWithTextFile

# Usage
Follow the on-screen prompts to navigate through the application. Create an account, log in, and perform banking operations as needed. Data is stored in the accounts.txt file.

# Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.

# License
This project is licensed under the MIT License - see the LICENSE file for details.

# Acknowledgments
This project was inspired by the need for a simple banking application for coding beginners.
Thanks to the Java programming language and its extensive standard libraries.
