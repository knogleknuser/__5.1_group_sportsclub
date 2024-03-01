SELECT
    member_id,
    name,
    address,
    m.zip,
    gender,
    city, year
FROM
    member AS m
        INNER JOIN zip USING(zip)
WHERE
        member_id = ?;