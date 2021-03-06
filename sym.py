#!/usr/bin/python3
import os
import pathlib

base = pathlib.Path(__file__).parent.absolute()

os.chdir(base)

apps = [
    "./src/main/java/app/onepass",
]

def sym(dst):
    os.chdir(base)
    os.chdir(dst)
    depth = len(dst.split("/")) - 1
    relative_path = "../" * depth + "apis/gen/java/app/onepass/apis"
    os.symlink(relative_path, "apis")

for app in apps:
    try:
        sym(app)
    except:
        pass
