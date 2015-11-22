#pragma once
#include <iostream>
#include <vector>
#include <cstring>
using namespace std;

class Node {
public:
    Node(string data, int pos);
    ~Node();
    int getXPos() const;
    string getData() const;
    void getChildren(vector<Node*>&);
    int findX() const;
    bool canMoveUp() const;
    bool canMoveDown() const;
    bool canMoveRight() const;
    bool canMoveLeft() const;
    bool isOrdered() const;
    int getDownPos() const;
    int getUpPos() const;
    int getRightPos() const;
    int getLeftPos() const;
    Node& moveX(int moveToIndex) const;
    void print(ostream& out) const;

public:
    string data;
    int x_pos;
};
