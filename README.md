LikeBook.si
===========

TODO

This is OpenShift git repostory for LikeBook.si application

Running on OpenShift
--------------------

SSH

    ssh 5158adf4e0b8cd2532000323@likebook-zitnik.rhcloud.com

OpenShift remote

    git remote add openshift ssh://5158adf4e0b8cd2532000323@likebook-zitnik.rhcloud.com/~/git/likebook.git/


MySQL 5.1
---------

MySQL 5.1 database added.  Please make note of these credentials:

    Root User: admintWvrGpB
    Root Password: 6AmADjm-_yaa
    Database Name: likebook

Connection URL: mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/

You can manage your new MySQL database by also embedding phpmyadmin-3.4.
The phpmyadmin username and password will be the same as the MySQL credentials above.

PHPMyAdmin 3.4
--------------

phpMyAdmin 3.4 added.  Please make note of these MySQL credentials again:

    Root User: admintWvrGpB
    Root Password: 6AmADjm-_yaa

URL: https://likebook-zitnik.rhcloud.com/phpmyadmin/
