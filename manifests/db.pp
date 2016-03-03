exec { "apt-update":
  command => "/usr/bin/apt-get update"
}
package { "mysql-server":
  ensure  => installed,
  require => Exec["apt-update"],
}

file { "/etc/mysql/conf.d/allow_external.cnf":
  owner => mysql,
  group => mysql,
  mode => 0644,
  content => template("/vagrant/manifests/allow_ext.cnf"),
  require => Package["mysql-server"],
  notify  => Service["mysql"],
}

service { "mysql":
  ensure     => running,
  enable     => true,
  hasstatus  => true,
  hasrestart => true,
  require    => Package["mysql-server"],
}

exec { "db_server":
  unless  => "mysql -uroot db_server",
  command => "mysqladmin -uroot create db_server",
  path    => "/usr/bin/",
  require => Service["mysql"],
}

exec { "remove-anonymous-user":
  command => "mysql -uroot -e \"DELETE FROM mysql.user \
                                WHERE user=''; \
                                FLUSH PRIVILEGES\" ",
  onlyif  => "mysql -u' '",
  path    => "/usr/bin",
  require => Service["mysql"],
}

exec { "catalog-user":
  unless  => "mysql -ucatalog -p123456 db_server",
  command => "mysql -uroot -e \"GRANT ALL PRIVILEGES ON \
                                db_server.* TO 'catalog'@'%' \
                                IDENTIFIED BY '123456';\" ",
  path    => "/usr/bin/",
  require => Exec["db_server"],
}