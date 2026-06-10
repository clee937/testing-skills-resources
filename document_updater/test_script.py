from faker import Faker
from pathlib import Path
import shutil

test_dir = Path("test_directory")

# Automatically delete if it exists
if test_dir.exists():
    print("Removing existing 'test_directory' ...")
    shutil.rmtree(test_dir)


fake = Faker("en_GB")

# Create directories
originals_dir = Path("test_directory/originals")
updates_dir = Path("test_directory/updates")

originals_dir.mkdir(parents=True, exist_ok=True)
updates_dir.mkdir(parents=True, exist_ok=True)

# Generate a fake person (only once)
first_name = fake.first_name()
last_name = fake.last_name()
address = fake.address()

# ------------------------------------

# Create file path (originals)
original_file_path = originals_dir / last_name

with open(original_file_path, "w") as f:
    f.write(first_name + " " + last_name + "\n")
    f.write(address + "\n")

print(f"Created {original_file_path}")

# ------------------------------------

# To create multiples
# people = []
# for i in range(3):
#     first_name = fake.first_name()
#     # last_name = fake.last_name()
#     address = fake.address()

    

#     people.append(f"{first_name} {last_name}")
    

#     file_path = originals_dir / last_name

#     with open(file_path, "w") as f:
#         f.write(f"{fake.first_name()} {last_name}\n")
#         f.write(address + "\n")
# print(people)

# print(f"Created {file_path}")

# ------------------------------------

# Create file path (updates) — same person
# update_file_path = updates_dir / last_name

# with open(update_file_path, "w") as f:
#     f.write(first_name + " " + last_name + "\n")
#     f.write(address + "\n")

# print(f"Created {update_file_path}")

# # ------------------------------------

# Create file path (updates) — same person as originals, different address
# update_file_path = updates_dir / last_name

# with open(update_file_path, "w") as f:
#     f.write(first_name + " " + last_name + "\n")
#     f.write(fake.address() + "\n")

# print(f"Created {update_file_path}")

# ------------------------------------

# Create file path (updates) — random person

# last_name_2 = fake.last_name()

# update_file_path = updates_dir / last_name_2

# with open(update_file_path, "w") as f:
#     f.write(fake.first_name() + " " + last_name_2 + "\n")
#     f.write(fake.address() + "\n")

# print(f"Created {update_file_path}")

# ------------------------------------

# To create multiples

# for i in range(3):
#     first_name = fake.first_name()
#     last_name = fake.last_name()

#     file_path = updates_dir / last_name

#     with open(file_path, "w") as f:
#         f.write(f"{first_name} {last_name}\n")
#         f.write(fake.address() + "\n")

# print(f"Created {file_path}")

# ------------------------------------

# Create allowlist file with the surname

# allowlist_file = Path("test_directory/allowlist")
# with open(allowlist_file, "w") as f:
#     f.write(last_name + "\n")

# print(f"Created {allowlist_file}")

# ------------------------------------

# Create droplist file with the surname

droplist_file = Path("test_directory/droplist")
with open(droplist_file, "w") as f:
    f.write(last_name + "\n")

print(f"Created {droplist_file}")

# ========================================

# Create droplist file with random surname

# droplist_file = Path("test_directory/droplist")
# with open(droplist_file, "w") as f:
#     f.write(fake.last_name() + "\n")

# print(f"Created {droplist_file}")

# ========================================


