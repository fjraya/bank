package com.iobuilders.bank;

import com.iobuilders.bank.account.application.ports.output.WalletPort;
import com.iobuilders.bank.account.application.service.WalletService;
import com.iobuilders.bank.account.infrastructure.out.WalletAdapter;
import com.iobuilders.bank.account.infrastructure.out.WalletRepository;
import com.iobuilders.bank.user.application.ports.output.UserPort;
import com.iobuilders.bank.user.application.service.UserService;
import com.iobuilders.bank.user.infrastructure.out.UserAdapter;
import com.iobuilders.bank.user.infrastructure.out.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Bean
	public UserPort userPort(UserRepository userRepository) {
		return new UserAdapter(userRepository);
	}

	@Bean
	public UserService userService(UserPort createUserPort) {
		return new UserService(createUserPort);
	}

	@Bean
	public WalletPort walletPort(WalletRepository walletRepository) {
		return new WalletAdapter(walletRepository);
	}

	@Bean
	public WalletService walletService(WalletPort walletPort) {
		return new WalletService(walletPort);
	}
}
