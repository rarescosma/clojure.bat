#!/usr/bin/env python2
"Watches the source dir for changes and calls marginalia"
import os
import pyinotify
import subprocess
import yaml
import sys
import signal
import time
import BaseHTTPServer
from SimpleHTTPServer import SimpleHTTPRequestHandler
from threading import Thread

DOT = os.path.dirname(os.path.realpath(__file__))

def bootThread(target):
    t = Thread(target=target)
    t.daemon = True
    t.start()

def startWebServer():
    os.chdir(DOT + "/../docs")
    SimpleHTTPRequestHandler.protocol_version = "HTTP/1.0"
    httpd = BaseHTTPServer.HTTPServer(
        ('127.0.0.1', 8000), SimpleHTTPRequestHandler)

    sa = httpd.socket.getsockname()
    print "Serving HTTP on", sa[0], "port", sa[1], "..."
    t = Thread(target=httpd.serve_forever)
    t.daemon = True
    t.start()

def threadLivereload():
    lrBin = DOT + "/../node_modules/.bin/livereload"
    watchDoc = DOT + "/../docs/"
    subprocess.call([lrBin, watchDoc, "-w", "100"])

def threadWatcher():
    wm = pyinotify.WatchManager()
    mask = pyinotify.IN_CREATE | pyinotify.IN_MODIFY
    notifier = pyinotify.Notifier(wm, PTmp())
    watchPath = os.path.normpath(os.path.join(DOT, "../src/bat"))
    print "Watching '%s' for changes ..." % watchPath
    wdd = wm.add_watch(watchPath, mask, rec=True)

    while True:
        notifier.process_events()
        if notifier.check_events():
            notifier.read_events()

class PTmp(pyinotify.ProcessEvent):
    "Processes file events"
    def process_IN_MODIFY(self, event):
        "MODIFY event handler"
        filename = os.path.join(event.path, event.name)
        print "Modified: %s " % filename
        print DOT
        subprocess.call([ DOT + "/marg.sh"])

if [__name__ == "__main__"]:
    os.setpgrp() # create new process group, become its leader
    try:
        startWebServer()
        bootThread(threadLivereload)
        bootThread(threadWatcher)
        while True:
            time.sleep(1)
    finally:
        os.killpg(0, signal.SIGKILL)
