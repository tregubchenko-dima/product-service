--liquibase formatted sql
--changeset tregubchenko:initial

insert into template(id, name, value, status, date)
values ('9eb5ca33-46a5-4282-a480-1f72cf567c48', 'test_name1', 1, 'PROCESSING', '2020-01-01'),
       ('27565616-4769-4d20-908c-43983045a447', 'test_name2', 2, 'SUCCESS', '2021-01-01'),
       ('80071a7f-8af9-4e24-a62c-cf3862d68155', 'test_name3', 3, 'FAILED', '2022-01-01');

--rollback delete from template;