import smtplib
import os
from email.message import EmailMessage

SMTP_HOST = "localhost"
SMTP_PORT = 1025

RECIPIENT = os.getenv("EMAIL_RECIPIENT")
SUBJECT = os.getenv("EMAIL_SUBJECT")
BODY = os.getenv("EMAIL_BODY")
SENDER = os.getenv("EMAIL_SENDER", "GitHub Actions <noreply@github.com>")

msg = EmailMessage()
msg.set_content(BODY)
msg['Subject'] = SUBJECT
msg['From'] = SENDER
msg['To'] = RECIPIENT

print("--- Notification Script Logs ---")
print(f"SMTP Server: {SMTP_HOST}:{SMTP_PORT}")
print(f"Sender: {SENDER}")
print(f"Recipient: {RECIPIENT}")
print(f"Subject: {SUBJECT}")
print("--- Email Body ---")
print(BODY)
print("--------------------")

if not all([RECIPIENT, SUBJECT, BODY]):
    print("Error: One or more required environment variables are empty.")
    sys.exit(1)

try:
    with smtplib.SMTP(SMTP_HOST, SMTP_PORT) as server:
        server.send_message(msg)
        print(f"Successfully sent notification to {RECIPIENT}")
except Exception as e:
    print(f"Error sending email: {e}")
    exit(1)