//Author: Singhtaravirak Chea
//Date: 02-Nov-2018

#include "stdafx.h"
#include<iostream>
#include"Shape.h"
#include"Circle.h"
#include"Rectangle.h"
using namespace std; 
using namespace shape; 

int main()
{
	cout << "***** Test Circle Class *****" << endl;
	Circle c(2);
	cout << c.getName() << " has radius " << c.getRadius() << " and area " << c.getArea() << endl << endl; 

	cout << "***** Test Rectangle Class *****" << endl; 
	Rectangle r(3, 4);
	cout << r.getName() << " has width " << r.getWidth() << " has height " << r.getHeight() << " and area " << r.getArea() << endl; 
	system("pause"); 
	return 0;
}

