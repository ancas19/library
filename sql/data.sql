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


INSERT INTO email_templates (subject, body)
VALUES (
    'USER_AND_PASSWORD',
    '
    <div class="content" style="margin-bottom: 20px; line-height: 1.6;">
        <h2 style="font-size: 20px; color: #4A90E2; margin-top: 0;">Welcome, <span>:name</span>!</h2>
        <p style="margin: 10px 0;">We are excited to have you join our Library App community. Below are your login credentials:</p>
        <div class="login-details" style="background-color: #f0f0f0; padding: 10px; border-radius: 8px; margin: 20px 0; font-size: 14px;">
            <p style="margin: 5px 0;"><strong>Username:</strong> <span>:username</span></p>
            <p style="margin: 5px 0;"><strong>Password:</strong> <span>:password</span></p>
        </div>
        <p style="margin: 10px 0;">To get started, click the button below to log in and explore our collection:</p>
        <p style="margin: 10px 0;">If you have any questions or need support, feel free to reach out to us at
           <a href="mailto:support@yourlibraryapp.com" style="color: #4A90E2;">support@yourlibraryapp.com</a>.
        </p>
        <p style="margin: 10px 0;">Happy reading!</p>
    </div>

	'
);