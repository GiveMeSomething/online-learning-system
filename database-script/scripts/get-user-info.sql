SELECT u.id, u.image, u.fullname, u.gender, u.email, r.rolename, u.address, u.statusid, s.value, u.mobile
FROM user u INNER JOIN role r
ON u.roleid = r.id
INNER JOIN status s
ON u.statusid = s.id;