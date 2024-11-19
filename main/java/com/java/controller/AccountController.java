package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.Account;
import com.java.entity.Transaction;
import com.java.repository.UserRepository;
import com.java.service.AccountService;
import com.java.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mybank/account-activity")
public class AccountController 
{
	@Autowired private AccountService accountService;
	@Autowired private TransactionService transactionService;
	@Autowired private UserRepository userRepository;
	
	@GetMapping("view-balance")
	public String viewCurrentBalance(Model model,HttpSession ses)
	{
		long accountno=(Long)ses.getAttribute("accountno");
		int amount=accountService.getCurrentBalance(accountno);
		model.addAttribute("amount",amount);
		return "transaction/show-balance";
	}
	@GetMapping("deposit-form")
	public String getDepositFormView()
	{
		return "transaction/deposit/deposit-money";
	}
	@GetMapping("deposit-money")
	public String depositMoney(int amount,Model model,HttpSession ses)
	{
		Transaction transaction=accountService.updateMoney(amount,(Long)ses.getAttribute("accountno"),"Credit");
		model.addAttribute("transaction",transaction);
		return "transaction/deposit/deposit-success";
	}
	@GetMapping("withdraw-form")
	public String getWithdrawFormView()
	{
		return "transaction/withdraw/withdraw-money";
	}
	@GetMapping("withdraw-money")
	public String withdrawMoney(int amount,Model model,HttpSession ses)
	{
		long accountno=(Long)ses.getAttribute("accountno");
		int camount=accountService.getCurrentBalance(accountno);
		if(amount>camount)
		{
			model.addAttribute("amount",amount);
			model.addAttribute("msg","Sorry!!! you do not have sufficeint amount");
			return "transaction/withdraw/withdraw-money";
		}
		model.addAttribute("amount",amount);
		Transaction transaction=accountService.updateMoney(-amount,accountno,"Debit");
		model.addAttribute("transaction",transaction);
		return "transaction/withdraw/withdraw-success";
	}
	@GetMapping("transaction-summary")
	public String getTransactionSummaryView(Model model,HttpSession ses)
	{
		List<Transaction> tlist=transactionService.getTransactionList((Long)ses.getAttribute("accountno"));
		model.addAttribute("tlist", tlist);
		return "transaction/statement/summary";
	}
	@GetMapping("accountno-form")
	public String getAccountNoFormView()
	{
		return "transaction/transfer/account-number";
	}
	@GetMapping("verify-accountno")
	public String verifyAccountNo(long accountno,Model model,HttpSession ses)
	{
		Long an=(Long)ses.getAttribute("accountno");
		if(an==accountno)
		{
			model.addAttribute("an", an);
			model.addAttribute("msg", "It is your own account no");
			return "transaction/transfer/account-number";
		}
		Account account=accountService.getAccount(accountno);
		if(account==null)
		{
			model.addAttribute("an", accountno);
			model.addAttribute("msg", "Entered account no does bot exist");
			return "transaction/transfer/account-number";
		}
		String name=userRepository.findName(account.getUserid());
		ses.setAttribute("rname",name);
		ses.setAttribute("raccountno",accountno);
		return "transaction/transfer/transfer-money";
	}
	@GetMapping("transfer-money")
	public String transferMoney(int amount,Model model,HttpSession ses)
	{
		Long an=(long)ses.getAttribute("accountno");
		int camount=accountService.getCurrentBalance(an);
		if(amount>camount)
		{
			model.addAttribute("amount", amount);
			model.addAttribute("msg","Sorry!!!you do not have sufficient amount");
			return "transaction/transfer/transfer-money";
		}
		Transaction tr=accountService.transferAmount(amount,an,(Long)ses.getAttribute("raccountno"));
		model.addAttribute("transaction",tr);
		return "transaction/transfer/transfer-success";
	}
}