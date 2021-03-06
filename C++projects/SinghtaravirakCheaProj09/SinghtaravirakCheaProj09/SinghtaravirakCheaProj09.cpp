//Author: Singhtaravirak Chea
//Date: 03-Dec-2018

#include "stdafx.h"
#include <iostream>
#include <string>
#include <cstdlib>
#include <vector>
#include <algorithm>
#include "Book.h"; 
using namespace std; 
using namespace BookDatabase;

void AddNewBook(vector<Book> &bookdata); 
void PrintBooks(vector<Book> &bookdata); 
void SortBooks(vector<Book> &bookdata); 
void PrintMenu(); 


int main()
{
	vector<Book> bookdata; 
	int choice; 

	do
	{
		PrintMenu(); 
		cin >> choice; 
		cin.ignore(); 
		switch (choice)
		{
		case 1: 
			AddNewBook(bookdata);
			break; 
		case 2: 
			PrintBooks(bookdata); 
			break; 
		}
	} while (choice != 3); 
	
	system("pause");
	return 0;
}

void AddNewBook(vector<Book> &bookdata)
{
	string newAuthor, newTitle, newDate; 

	cout << "Enter author name: " << endl; 
	getline(cin, newAuthor);
	cout << "Enter book title:" << endl; 
	getline(cin, newTitle); 
	cout << "Enter book date:" << endl; 
	getline(cin, newDate); 
	Book b(newAuthor, newTitle, newDate); 
	bookdata.push_back(b); 
}
void PrintBooks(vector<Book> &bookdata)
{
	SortBooks(bookdata);
	cout << "The books entered so far, sorted by author:" << endl;
	for (unsigned int i = 0; i < bookdata.size(); i++)
	{
		cout << " " << bookdata[i].getAuthor() << ", " <<
			bookdata[i].getTitle() << ", " <<
			bookdata[i].getDate() << endl;
	}
}
void SortBooks(vector<Book> &bookdata)
{
	sort(bookdata.begin(), bookdata.end());
}
void PrintMenu()
{
	cout << endl << "Select from the following choices: " << endl; 
	cout << "1. Add new book" << endl; 
	cout << "2. Print listing sorted by author" << endl; 
	cout << "3. Quit" << endl; 
}

