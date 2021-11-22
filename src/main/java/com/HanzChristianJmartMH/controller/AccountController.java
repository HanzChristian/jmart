package com.HanzChristianJmartMH.controller;// TODO sesuaikan dengan package Anda: package com.alvinJmartRK.controller;


// TODO sesuaikan dengan package Anda: import com.alvinJmartRK.Account;
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
public class AccountController implements BasicGetController<Account>
{
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
	@JsonAutowired(value = Account.class, filepath = "testing")
	public static JsonTable<Account> accountTable;

	@GetMapping
	String index(){
		return "account page";
	}

	public JsonTable<Account> getJsonTable(){
		return accountTable;
	}

	@PostMapping("/login")
	Account login(@RequestParam String email, @RequestParam String password){
			MessageDigest mDigest = null;
			try {
				mDigest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			byte[] digest = mDigest.digest(password.getBytes());
			BigInteger No = new BigInteger(1, digest);
			String hash = No.toString(16);
			while (hash.length() < 32) hash = "0" + hash;
			String finalHash = hash;

			return Algorithm.<Account>find(accountTable, obj -> obj.email.equals(email) && obj.password.equals(finalHash));
		}

	@PostMapping("/register")
	Account register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		if(name.isBlank()) return null;
		Matcher firstMatcher = REGEX_PATTERN_EMAIL.matcher(email);
		if(!firstMatcher.find()) return null;
		Matcher secondMatcher = REGEX_PATTERN_PASSWORD.matcher(password);
		if(!secondMatcher.find()) return null;
		if(Algorithm.<Account>find(accountTable, obj -> obj.email.equals(email)) != null) return null;

		MessageDigest mDigest = null;
		try {
			mDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] digest = mDigest.digest(password.getBytes());
		BigInteger no = new BigInteger(1, digest);
		String hash = no.toString(16);
		while (hash.length() < 32) hash = "0" + hash;
		Account a = new Account(name, email, hash, 0);

		accountTable.add(a);
		return a;
	}

	@PostMapping("/{id}/registerStore")
	Store registerStore(@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber){
		Account acc = Algorithm.<Account> find(accountTable, obj -> obj.id == id);
		if(acc == null || acc.store != null)
		{
			return null;
		}
		acc.store = new Store(name, address, phoneNumber, 0.0);
		return acc.store;
	}

	@PostMapping("/{id}/topUp")
	public boolean topUp(@PathVariable int id,@RequestParam double balance){
		Account acc = getByID(id);
		if(acc != null)
		{
			acc.balance += balance;
			return true;
		}
		return false;
	}
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