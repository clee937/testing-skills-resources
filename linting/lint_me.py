"""
This is a file for trying out Pylint
"""
import os
import this_does_not_exist

def Open_Foo_File():
    try:
        os.chdir("subdir")
        filedesc = open("foo.txt", "w")
        contents = filedesc.readlines()
        filedesc.close()
    except FilNotFound:
        print("couldn't find the file or directory")
    return contents

if __name__ == "__main__":
     Open_Foo_File()