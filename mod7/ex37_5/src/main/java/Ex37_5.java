import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* WORKING LINK
    http://localhost:8080/ex37_5/index.html
*/

@WebServlet("/Ex37_5")
public class Ex37_5 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String amountStr = request.getParameter("loanAmount");
        String rateStr = request.getParameter("annualInterestRate");
        String yearsStr = request.getParameter("numYears");
        double amount = Double.parseDouble(amountStr);
        int years = Integer.parseInt(yearsStr);
        double annualRate = Double.parseDouble(rateStr);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Loan loan = new Loan(annualRate, years, amount);

        out.println("<html><body>");

        out.println("Loan Amount: " + amount + "<br>");
        out.println("Annual Interest Rate: " + annualRate + "<br>");
        out.println("Number of Years: " + years + "<br>");
        out.println("Monthly Payment: " + loan.getMonthlyPayment() + "<br>");
        out.println("Total Payment: " + loan.getTotalPayment());

        out.println("</body></html>");
        out.close();
    }
}

class Loan {
  private double annualInterestRate;
  private int numberOfYears;
  private double loanAmount;
  private java.util.Date loanDate;

  /** No-arg constructor */
  public Loan() {
    this(2.5, 1, 1000);
  }

  /** Construct a loan with specified annual interest rate,
      number of years, and loan amount
    */
  public Loan(double annualInterestRate, int numberOfYears,
      double loanAmount) {
    this.annualInterestRate = annualInterestRate;
    this.numberOfYears = numberOfYears;
    this.loanAmount = loanAmount;
    loanDate = new java.util.Date();
  }

  /** Return annualInterestRate */
  public double getAnnualInterestRate() {
    return annualInterestRate;
  }

  /** Set a new annualInterestRate */
  public void setAnnualInterestRate(double annualInterestRate) {
    this.annualInterestRate = annualInterestRate;
  }

  /** Return numberOfYears */
  public int getNumberOfYears() {
    return numberOfYears;
  }

  /** Set a new numberOfYears */
  public void setNumberOfYears(int numberOfYears) {
    this.numberOfYears = numberOfYears;
  }

  /** Return loanAmount */
  public double getLoanAmount() {
    return loanAmount;
  }

  /** Set a newloanAmount */
  public void setLoanAmount(double loanAmount) {
    this.loanAmount = loanAmount;
  }

  /** Find monthly payment */
  public double getMonthlyPayment() {
    double monthlyInterestRate = annualInterestRate / 1200;
    double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
      (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
    return monthlyPayment;    
  }

  /** Find total payment */
  public double getTotalPayment() {
    double totalPayment = getMonthlyPayment() * numberOfYears * 12;
    return totalPayment;    
  }

  /** Return loan date */
  public java.util.Date getLoanDate() {
    return loanDate;
  }
}