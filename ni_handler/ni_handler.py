import random

# NI numbers: test data

# The last letter: The final suffix is always A, B, C, or D

# All prefixes are valid except:

# The characters D, F, I, Q, U, and V are not used as either the first or second letter of a NINO prefix.
# The letter O is not used as the second letter of a prefix.
# Prefixes BG, GB, KN, NK, NT, TN and ZZ are not to be used.


def generate_prefix():
    banned_prefixes = {'BG', 'GB', 'KN', 'NK', 'NT', 'TN', 'ZZ'}

    prefix_chars_1 = 'ABCEGHJKLMNOPRSTWXYZ'
    prefix_chars_2 = 'ABCEGHJKLMNPRSTWXYZ'

    while True:
        prefix = (random.choice(prefix_chars_1) + random.choice(prefix_chars_2))

        if prefix not in banned_prefixes:
            return prefix

def generate_number():
    return f"{random.randint(0, 999999):06d}"

def generate_suffix():
    return random.choice('ABCD')

def generate_ni_number():

    prefix = generate_prefix()
    number = generate_number()
    suffix = generate_suffix()

    # Two valid formats
    formats = [
        f"{prefix} {number[:2]} {number[2:4]} {number[4:]} {suffix}",
        f"{prefix}{number}{suffix}"
    ]

    return random.choice(formats)

# Avoid duplicates using set()
def generate_batch(n):
    results = set()

    while len(results) < n:
        results.add(generate_ni_number())

    return results

ni_numbers = generate_batch(1000)


# Create and write to a file
with open("nino.txt", "w") as f:
    for ni in ni_numbers:
        f.write(ni + "\n")

# # Create and write to a file
# with open("nino.txt", "a") as f:
#     f.write(f"{generate_ni_number()}\n")

# Open and read the file after the appending:
# with open("nino.txt") as f:
#     print(f"{f.read()}")


# Next steps: Refactor so that AB123456C and AB 12 34 56 C are considered as duplicates.
