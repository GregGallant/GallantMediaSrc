#!/usr/bin/python

import sys
import os
import re
import time

### full deploy
appDir = "/var/www/GallantMediaSrc"
buildDir = appDir + "/build/libs/"
tomcatDir = "/usr/local/bin/tomcat/webapps/"
shutdown = "/usr/local/bin/tomcat/bin/shutdown.sh"
startup = "/usr/local/bin/tomcat/bin/startup.sh"


gradle_script = appDir + "/build.gradle"

def deploy():

    os.system("clear")

    if (len(sys.argv) < 2):
       print("Hot deploy...")
       hotdeploy()
       sys.exit(1)

    if (sys.argv[1] != "--full" and sys.argv[1] != "-f"):
        print("Usage: --full or -f.\nExiting...")
        sys.exit(1)


    appBuild = getVersion()
    if (appBuild == None):
        print("Version Not Found... exiting.")
        sys.exit(1)

    appName = appBuild[1]+"-"+appBuild[0]
    baseName = appBuild[1]

    print("______________" +appName+ "___________________________")
    print("Listing build directory...")
    print("_____________________________________________")
    removeCommand = "rm -rf " + tomcatDir + baseName+"-*"
    removeCommand2 = "rm -rf " + tomcatDir + "ROOT"
    copyCommand = "cp " + buildDir + appName + ".war " + tomcatDir + " "
    moveCommand = "mv " + tomcatDir + appName + " " + tomcatDir + "ROOT"

    # start

    os.system(removeCommand)
    os.system(removeCommand2)
    os.system(copyCommand)
    os.system(shutdown)
    os.system(startup)

    while not os.path.exists(tomcatDir + appName):
        time.sleep(3)

    os.system(moveCommand)

    #print(removeCommand)
    #print(copyCommand)
    #print(shutdown)
    #print(startup)
    #print(moveCommand)





def hotdeploy():


    # Hot deploy stuff
    staticDir = appDir + "/src/main/resources/static"
    liveStatic = tomcatDir + "ROOT/WEB-INF/classes/static/"

    print("cp -r " + staticDir + "/* " + liveStatic)
    os.system("cp -r " + staticDir + "/* " + liveStatic)


# Get baseName and version from build.gradle
def getVersion():

    appVersion = None
    baseName = None

    gradle_build = open(gradle_script, "r")
    for line in gradle_build:
        versionMatch = re.search(r'version[\s]+=[\s]+\'([0-9\.]{5,9})\'', line)
        baseNameMatch = re.search(r'baseName[\s]+=[\s]+\'([A-Za-z0-9\.]+)\'', line)
        if(versionMatch):
            #print(line)
            appVersion = versionMatch.group(1)
        if(baseNameMatch):
            baseName = baseNameMatch.group(1)

    if(appVersion != None and baseName != None):
        return [appVersion, baseName]

    return None
deploy()
