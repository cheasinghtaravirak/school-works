#include "stdafx.h"
#include"Shape.h"
#include<string>

namespace shape
{
	Shape::Shape()
	{
		name = ""; 
	}
	Shape::Shape(string name)
	{
		this->name = name; 
	}
	string Shape::getName()
	{
		return this->name; 
	}
	void Shape::setName(string newName)
	{
		this->name = newName; 
	}
	double Shape::getArea()
	{
		return 0; 
	}
}