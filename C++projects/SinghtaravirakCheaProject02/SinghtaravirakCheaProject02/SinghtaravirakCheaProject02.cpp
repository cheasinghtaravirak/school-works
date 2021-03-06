#include "stdafx.h"
#include<iostream> 
using namespace std; 

/* Do Programming Project 2 on page 611. You may watch the video note for
* Programming Project 1 on page 611 to get some idea. Then try to finish
* independently by complete this sample file.
*
* Author: Singhtaravirak Chea
* Version: 10-Sept-2018
*/

#include <iostream>
using namespace std;

class CDAccount
{
public:
	CDAccount();
	CDAccount(double new_balance, double new_interest_rate, int new_term);
	double get_initial_balance() const;
	double get_balance_at_maturity() const;
	double get_interest_rate() const;
	int get_term() const;
	void input(istream& in);
	void output(ostream& out); 

private:
	int dollar;
	int cent;
	double interest_rate;
	int term; // months until maturity (1 -> 12) 
};

int main()
{
	// You implement the test code here
	// You may watch the video note to get the idea
	// notice that user will only enter balance as a double value
	// You may implement it as a menu oriented testing program
	// which be able to test constructors, methods of CDAccount class

	cout << "A Program to Test  constructors, methods of CDAccount class: " << endl; 
	CDAccount myAccount;
	int choice; 
	do {
		cout << "*****************************************\n"; 
		cout << "Choose 1 to test default constructor.\n";
		cout << "Choose 2 to test the second constructor.\n";
		cout << "Choose 3 to test get_initial_balance() method.\n";
		cout << "Choose 4 to test get_balance_at_maturity() method.\n";
		cout << "Choose 5 to test get_interest_rate() method.\n";
		cout << "Choose 6 to test get_term() method. \n";
		cout << "Choose 7 to exit the testing. \n";
		cout << "*****************************************\n";
		cout << "Enter the number: ";
		cin >> choice;
		switch (choice) {
		case 1:
			myAccount.output(cout);
			break;
		case 2:
			myAccount.input(cin);
			myAccount = CDAccount(myAccount.get_initial_balance(), myAccount.get_interest_rate(), myAccount.get_term());
			myAccount.output(cout);
			break;
		case 3:
			cout << "The initial balance is " << myAccount.get_initial_balance() << endl;
			break;
		case 4:
			cout << "The balance at maturity is " << myAccount.get_balance_at_maturity() << endl;
			break;
		case 5:
			cout << "The interest rate is " << myAccount.get_interest_rate() << endl;
			break;
		case 6:
			cout << "The term is " << myAccount.get_term() << endl;
			break;
		case 7:
			cout << "Thank you\n";
		}
	} while (choice != 7); 
	system("pause"); 
	return 0; 
}

// Implement the class definition here. Notice that the interfaces are the
// same as in Programming Project 1. However, the implementation will be
// different since the private information balance is stored differently
// for example, the input function will only read the balance, interest rate
// and term from the user. So it should be modified as following
void CDAccount::input(istream& in)
{
	cout << "Enter your inital balance: "; 
	double balance;
	in >> balance; // get initial balance from user
	// convert balance to dollars and cents since this is how balance stored
	dollar = (int)balance; // dollar is the interger part of balace
	cent = (int)((balance - dollar) * 100); // cent is the fraction part * 100
	cout << "Enter your interest rate (in a fraction format): "; 
	in >> interest_rate; // get interest rate from user
	cout << "Enter the term (1-12): "; 
	in >> term; // get term from user
}

CDAccount::CDAccount() : dollar(0), cent(0), interest_rate(0.0), term(0) {

}
CDAccount::CDAccount(double new_balance, double new_interest_rate, int new_term) {
	dollar = (int)new_balance; 
	cent = (int)((new_balance - dollar) * 100 + 0.5);
	interest_rate = new_interest_rate; 
	term = new_term; 
}

double CDAccount::get_initial_balance() const {
	double initial_balance = dollar + cent / 100.0; 
	//double initial_balance = static_cast<double>(dollar + cent / 100.0); 
	return initial_balance; 
}

double CDAccount::get_balance_at_maturity() const {
	double initial_balance = get_initial_balance(); 
	double balance_at_maturity = initial_balance + initial_balance * interest_rate * (term / 12.0);
	return balance_at_maturity; 
}

double CDAccount::get_interest_rate() const {
	return interest_rate; 
}

int CDAccount::get_term() const {
	return term; 
}

void CDAccount::output(ostream& out) { 
	out.setf(ios::fixed);
	out.setf(ios::showpoint);
	out.precision(2);
	out << "Account balance $" << get_balance_at_maturity() << endl; 
	out << "Interest rate " << get_interest_rate() * 100 << "%" << endl;
	out << "Term " << get_term() << endl; 
}

/*
A Program to Test  constructors, methods of CDAccount class:
*****************************************
Choose 1 to test default constructor.
Choose 2 to test the second constructor.
Choose 3 to test get_initial_balance() method.
Choose 4 to test get_balance_at_maturity() method.
Choose 5 to test get_interest_rate() method.
Choose 6 to test get_term() method.
Choose 7 to exit the testing.
*****************************************
Enter the number: ?
*/
