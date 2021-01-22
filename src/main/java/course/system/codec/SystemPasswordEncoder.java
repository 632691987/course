package course.system.codec;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SystemPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		return DigestUtils.encodeSHA512Hex(rawPassword.toString().getBytes());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return DigestUtils.encodeSHA512Hex(rawPassword.toString().getBytes()).equalsIgnoreCase(encodedPassword);
	}

}
