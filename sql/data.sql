---- memeberships
INSERT INTO public.memberships
(id, membership_type, loan_limit, loan_period_days, grace_period_days, created_at, user_created, updated_user, updated_at)
VALUES(1, 'NORMAL', 5, 10, 0, '2024-10-18 23:33:13.416', 'SYSTEM', NULL, '2024-10-18 23:33:13.416');
INSERT INTO public.memberships
(id, membership_type, loan_limit, loan_period_days, grace_period_days, created_at, user_created, updated_user, updated_at)
VALUES(2, 'PREMIUM', 10, 15, 5, '2024-10-18 23:33:13.465', 'SYSTEM', NULL, '2024-10-18 23:33:13.465');
INSERT INTO public.memberships
(id, membership_type, loan_limit, loan_period_days, grace_period_days, created_at, user_created, updated_user, updated_at)
VALUES(3, 'EMPLOYEES', 15, 20, 10, '2024-10-18 23:33:13.482', 'SYSTEM', NULL, '2024-10-18 23:33:13.482');

---- roles
INSERT INTO public.roles
(id, role_name, created_at, user_created, updated_user, updated_at)
VALUES(1, 'USER', '2024-10-18 23:04:09.852', 'SYSTEM', NULL, '2024-10-18 23:04:09.852');
INSERT INTO public.roles
(id, role_name, created_at, user_created, updated_user, updated_at)
VALUES(2, 'EMPLOYEE', '2024-10-18 23:04:09.887', 'SYSTEM', NULL, '2024-10-18 23:04:09.887');
INSERT INTO public.roles
(id, role_name, created_at, user_created, updated_user, updated_at)
VALUES(3, 'ADMIN', '2024-10-18 23:04:09.902', 'SYSTEM', NULL, '2024-10-18 23:04:09.902');