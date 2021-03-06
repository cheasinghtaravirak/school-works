//Author: Singhtaravirak Chea
//Date: 03-Oct-2018

#include "stdafx.h"
#include<iostream>
#include"SinghtaravirakCheaMoney.h"
#include<fstream>
using namespace std; 

int main()
{
	Money amount;
	ifstream in_stream;
	ofstream out_stream;

	in_stream.open("infile.txt");
	if (in_stream.fail())
	{
		cout << "Input file opening failed.\n";
		exit(1);
	}

	out_stream.open("outfile.txt");
	if (out_stream.fail())
	{
		cout << "Output file opening failed.\n";
		exit(1);
	}
	in_stream >> amount;
	out_stream << amount
		<< " copied from the file infile.txt.\n";
	cout << amount
		<< " copied from the file infile.txt.\n";

	in_stream.close();
	out_stream.close();
	
	system("pause"); 

	return 0;

}

