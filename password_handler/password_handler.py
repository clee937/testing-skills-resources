import hmac
import hashlib
import itertools

# WARNING - this is code only for a course exercise and should not be used for
# passwords in the real world!

key = "super secret key which nobody knows"

def hide_password(pw):
    return hmac.new(bytes(key, 'utf-8'), bytes(pw, 'utf-8'), hashlib.sha256).digest()

def check_password(sig, pw):
    return hmac.compare_digest(hide_password(pw), sig)


def password_finder(hashed_pw):
    chars = 'abcdefghijklmnopqrstuvwxyz0123456789'
    
    for first in chars:
        for second in chars:
            for third in chars:
                for fourth in chars:
                    password = first + second + third + fourth
                    if check_password(hashed_pw, password):
                        print(f"Password is {password}")
                        break


hashed_pw = hide_password("siv7")
# print(f"The password is {output}")

# password_finder(output)


# Using itertools - built-in Python module
def itertools_example():
    return [combo for combo in itertools.combinations(["A", "B", "C"], 2)]

# [('A', 'B'), ('A', 'C'), ('B', 'C')]

# print(itertools_example())

def itertools_password_finder(hashed_pw):
    chars = 'abcdefghijklmnopqrstuvwxyz0123456789'
    
    for combo in itertools.product(chars, repeat=4):
        password = ''.join(combo)
        if check_password(hashed_pw, password):
            print(f"Password found: {password}")
            break


itertools_password_finder(hashed_pw)



