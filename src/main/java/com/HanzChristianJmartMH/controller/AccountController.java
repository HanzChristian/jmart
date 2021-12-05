package com.HanzChristianJmartMH.controller;


import com.HanzChristianJmartMH.Account;
import com.HanzChristianJmartMH.Algorithm;
import com.HanzChristianJmartMH.Store;
import com.HanzChristianJmartMH.dbjson.JsonAutowired;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

	@JsonAutowired(value = Account.class, filepath = "D:\\Perkuliahan\\Semester 5\\Praktikum\\OOP\\Modul 1\\Folder khusus\\jmart\\src\\GoldenSample\\account.json")
	public static JsonTable<Account> accountTable;

	public JsonTable<Account> getJsonTable() {
		return accountTable;
	}

	@PostMapping("/login")
	public Account login(@RequestParam String email, @RequestParam String password) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		assert messageDigest != null;
		byte[] digest = messageDigest.digest(password.getBytes());
		BigInteger No = new BigInteger(1, digest);
		StringBuilder hash = new StringBuilder(No.toString(16));
		while (hash.length() < 32) hash.insert(0, "0");
		String finalHash = hash.toString();

		return Algorithm.<Account>find(accountTable, obj -> obj.email.equals(email) && obj.password.equals(finalHash));
	}

	@PostMapping("/register")
	Account register
			(
					@RequestParam String name,
					@RequestParam String email,
					@RequestParam String password
			)
	{
		Matcher emailMatcher = REGEX_PATTERN_EMAIL.matcher(email);
		boolean emailMatch = emailMatcher.find();
		Matcher passwordMatcher = REGEX_PATTERN_PASSWORD.matcher(password);
		boolean passwordMatch = passwordMatcher.find();
		boolean unique = true;

		for(Account acc: accountTable){
			if(acc.email.equals(email)){
				unique = false;
				break;
			}
		}

		if(!name.isBlank() && emailMatch && passwordMatch && unique){

			Account regAccount = new Account(name, email, hashPassword(password), 0);
			accountTable.add(regAccount);
			return regAccount;

		} else {
			return null;
		}
	}

	@PostMapping("/{id}/registerStore")
	Store registerStore(@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber) {
		Account a = Algorithm.<Account>find(accountTable, obj -> obj.id == id);
		if (a == null || a.store != null) {
			return null;
		}
		a.store = new Store(name, address, phoneNumber, 0.0);
		return a.store;
	}

	@PostMapping("/{id}/topUp")
	boolean topUp(@PathVariable int id, @RequestParam double balance) {
		Account account = getById(id);
		if (account != null) {
			account.balance += balance;
			return true;
		}
		return false;
	}


	public String hashPassword(String password){
		try{
			String generatedPassword;

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for (byte aByte : bytes) {
				sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
			return generatedPassword;
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
			return password;
		}
	}

	@GetMapping
	String index() {
		return "account page";
	}

	@GetMapping("/{id}")
	public Account getByAccountId(@PathVariable int id) { return getById(id); }
}

//	@GetMapping
//	String index() { return "account page"; }
//
//	@PostMapping("/register")
//	Account register(@RequestParam String name, @RequestParam String email, @RequestParam String password)
//	{
//		return new Account(name, email, password, 0);
//	}
	
//	@GetMapping("/{id}")
//	String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
// }