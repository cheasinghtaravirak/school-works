#include "stdafx.h"
#include"Rectangle.h"
namespace shape
{
	Rectangle::Rectangle() : Shape("Rectangle"), width(0), height(0)
	{

	}
	Rectangle::Rectangle(double newWidth, double newHeight) : Shape("Rectangle"), width(newWidth), height(newHeight)
	{

	}
	double Rectangle::getWidth() 
	{
		return width; 
	}
	double Rectangle::getHeight()
	{
		return height; 
	}
	void Rectangle::setWidth(double newWidth)
	{
		this->width = newWidth; 
	}
	void Rectangle::setHeight(double newHeight)
	{
		this->height = newHeight; 
	}
	double Rectangle::getArea()
	{
		return width * height; 
	}

}