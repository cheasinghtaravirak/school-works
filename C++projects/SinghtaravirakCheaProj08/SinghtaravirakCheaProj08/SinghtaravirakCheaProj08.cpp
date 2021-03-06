//Author: Singhtaravirak Chea 
//Date: 17-Nov-2018 
#include "stdafx.h"
#include <iostream>
#include <string>
#include <cstdlib>
#include "Set.cpp"
using namespace std;
using namespace myset; 

template<class T>
void output_set(Set<T> myset)
{
	T *ptr = myset.to_array(); 
	if (ptr != NULL)
	{
		for (int i = 0; i < myset.size(); i++)
			cout << ptr[i] << endl; 
		delete[] ptr; 
	}
	else 
	{
		cout << "The Set is empty." << endl; 
	}
}

int main()
{
	Set<int> int_set; 
	int_set.add(4);
	int_set.add(5); 
	int_set.add(6); 
	int_set.add(4); 
	int_set.add(7); 

	cout << "Contents of int_set: " << endl; 
	output_set(int_set);

	cout << "Contains 4: " << int_set.contains(3) << endl; 
	cout << "Contains 10: " << int_set.contains(10) << endl; 

	cout << "Removing 4, 5, 10" << endl; 
	int_set.remove(4); 
	int_set.remove(5); 
	int_set.remove(10); 
	output_set(int_set); 
	cout << endl; 

	Set<int> new_int_set = int_set; 
	new_int_set.add(200); 
	cout << "New int set with 200 added " << endl; 
	output_set(new_int_set); 
	cout << "Original Set" << endl; 
	output_set(int_set); 

	Set<string> str_set; 
	str_set.add("Pink"); 
	str_set.add("Yellow");
	str_set.add("Orange"); 
	cout << "String set: " << endl; 
	output_set(str_set); 

	str_set.remove("Pink");
	str_set.remove("Yellow");
	str_set.remove("Orange");
	cout << "String set after removing all items: " << endl;
	output_set(str_set);




	system("pause"); 
	return 0; 
}

