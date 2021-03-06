//Author: Singhtaravirak Chea
//Date: 13-Nov-2018

#include "stdafx.h"
#include <iostream>
#include<cstdlib>
#include<string>
using namespace std; 

class InvalidNumberException
{};

int convert_hexTo_int(string str) throw (InvalidNumberException); 

int main()
{
	string hexNum;
	int decNum;
	char choice; 
	do
	{
		try
		{
			cout << "Type in the hex number you want to convert: \n";
			cin >> hexNum;
			decNum = convert_hexTo_int(hexNum);
			cout << "Here is the equivalent decimal value: \n" << decNum << endl;
		}
		catch (InvalidNumberException)
		{
			cout << "Invalid hexadecimal number input" << endl;
		}
		
		cout << "Do you want to do another conversion? (y/n) \n"; 
		cin >> choice; 
	} while (choice == 'y' || choice == 'Y');
	

	system("pause"); 
	return 0;
}


// Function to convert hexadecimal to decimal 
int convert_hexTo_int(string hexVal) throw (InvalidNumberException)
{
	int len = hexVal.length(); 

	// Initializing base value to 1, i.e 16^0 
	int base = 1;

	int dec_val = 0;

	// Extracting characters as digits from last character 
	for (int i = len - 1; i >= 0; i--)
	{
		// if character lies in '0'-'9', converting 
		// it to integral 0-9 by subtracting 48 from 
		// ASCII value. 
		//'0'->'9' = 48->57
		
		if (hexVal[i] >= '0' && hexVal[i] <= '9')
		{
			dec_val += (hexVal[i] - 48)*base;

			// incrementing base by power 
			base = base * 16;
		}

		// if character lies in 'A'-'F' , converting 
		// it to integral 10 - 15 by subtracting 55 
		// from ASCII value 
		// 'A'->'F' = 65->70
		else if (hexVal[i] >= 'A' && hexVal[i] <= 'F')
		{
			dec_val += (hexVal[i] - 55)*base;
			// incrementing base by power 
			base = base * 16;
		}
		else
		{
			throw InvalidNumberException(); 
		}
	}

	return dec_val;
}