/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nerdywoof.calculatorchallenge;

/**
 *
 * @author Alex Taberner
 */
public class MathFunction
{
    private double A;
    private double B;

    public MathFunction(double a, double b)
    {
        this.A = a;
        this.B = b;
    }

    public double multiplication()
    {
        return this.A * this.B;
    }

    public double division()
    {
        if (this.B == 0) throw new ArithmeticException("Can't divide by zero!");
        return this.A / this.B;
    }

    public double subtraction()
    {
        return this.A - this.B;
    }

    public double addition()
    {
        return this.A + this.B;
    }

    public double exponential()
    {
        return Math.pow(this.A, this.B);
    }
}
