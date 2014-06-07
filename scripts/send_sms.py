#!/usr/bin/python

__author__ = 'rmuhamedgaliev'

import atom
import gdata.calendar
import gdata.calendar.service
import random
import time
import sys

def send_sms(Title, Content, Where):
    cs = gdata.calendar.service.CalendarService()
    cs.email = "nagios@expospark.com"
    cs.password = "aR33nE4kaQ5x"
    cs.source = "Google-Calendar-SMS-5.0_" + str(random.randint(0, 10000))
    cs.ProgrammaticLogin()
    event = gdata.calendar.CalendarEventEntry()
    event.title = atom.Title(text=Title)
    event.content = atom.Content(text=Content)
    event.where.append(gdata.calendar.Where(value_string=Where))
    start_time = time.strftime("%Y-%m-%dT%H:%M:%S.000Z", time.gmtime(time.time() + 2 * 60))
    when = gdata.calendar.When(start_time=start_time, end_time=start_time)
    reminder = gdata.calendar.Reminder(minutes=1, extension_attributes={"method":"sms"})
    when.reminder.append(reminder)
    event.when.append(when)
    new_event = cs.InsertEvent(event, "/calendar/feeds/default/private/full")

def main():
    Title = sys.argv[1]
    Content = sys.argv[2]
    Where = sys.argv[3]
    send_sms(Title, Content, Where)

if __name__ == "__main__":
    main()
