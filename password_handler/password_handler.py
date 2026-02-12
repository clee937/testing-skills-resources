import hmac
import hashlib

# WARNING - this is code only for a course exercise and should not be used for
# passwords in the real world!

key = "super secret key which nobody knows"

def hide_password(pw):
    return hmac.new(bytes(key, 'utf-8'), bytes(pw, 'utf-8'), hashlib.sha256).digest()

def check_password(sig, pw):
    return hmac.compare_digest(hide_password(pw), sig)