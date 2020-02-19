#include "stdafx.h"
#include"Circle.h"

namespace shape
{
	Circle::Circle() : Shape("Circle"), radius(0)
	{

	}
	Circle::Circle(int theRadius) : Shape("Circle"), radius(theRadius)
	{

	}
	void Circle::setRadius(int newRadius)
	{
		this->radius = newRadius; 
	}
	double Circle::getRadius()
	{
		return radius; 
	}
	double Circle::getArea()
	{
		return 3.14159 * radius * radius; 
	}
}