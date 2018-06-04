#include <iostream>
#include <fstream>
#include <typeinfo>
#include <cstdlib>
#include <vector>
#include <list>
using namespace std;
int main(int argc, char * argv[])
{
    ifstream myfile("in-losowy.txt");
    int num = 2295224;
    int* bity = new int[num];
    int bit = 0;
    string line;
    cout << " Bith"<<endl;
    for (int i = 0; i < num; i++){
    	myfile >> line;
    	bity[i] = atoi(line.c_str());
    }

    std::vector<int> v;
    bit = bity[0];
    int tmp = 1;
    for(int i=1; i<num; i++){
    	if(i % 100000 == 0){
    		cout << i <<endl;
    	}
    	tmp =1;
    	while(bity[i] == bit && i < num){
    		i++;
    		tmp++;
    	}
    	bit = bity[i];
    	// konwersja
    	list<int> l_temp;
    	while((tmp / 256) > 0){
    		l_temp.push_front(tmp % 256);
    		tmp = tmp / 256;
    	}
    	l_temp.push_front(tmp % 256);
    	std::list<int>::iterator it; 
    	for (int j = 0; j < l_temp.size() - 1; j++){
    		v.push_back(0);
    	}
    	for (it=l_temp.begin(); it != l_temp.end(); it ++){
    		v.push_back(*it);
    	}
    	

    }
    cout << v.size()<<endl;
 //    for(std::vector<int>::iterator it = v.begin(); it != v.end(); ++it) {
 //    	cout << *it << " ";
	// }
    

    return 0;
}