#!/bin/sh

echo "$SMTP_RELAY $SMTP_USERNAME:$SMTP_PASSWORD" > /etc/postfix/sasl_passwd

postconf -e "compatibility_level = 3.6"

postconf -e "smtp_tls_security_level = encrypt"

chmod 600 /etc/postfix/sasl_passwd
# Create the Postfix lookup table from the password file
postmap /etc/postfix/sasl_passwd

# Set correct permissions for the password files
chmod 600 /etc/postfix/sasl_passwd /etc/postfix/sasl_passwd.db

# Start the Postfix service in the foreground
postfix start-fg
