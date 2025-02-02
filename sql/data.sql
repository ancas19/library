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
---- genres

INSERT INTO GENRES (VALUE, USER_CREATED)
VALUES ('SCIENCE FICTION', 'ADMIN');

INSERT INTO GENRES (VALUE, USER_CREATED)
VALUES ('FANTASY', 'ADMIN');

INSERT INTO GENRES (VALUE, USER_CREATED)
VALUES ('MYSTERY', 'ADMIN');

INSERT INTO GENRES (VALUE, USER_CREATED)
VALUES ('ROMANCE', 'ADMIN');

INSERT INTO GENRES (VALUE, USER_CREATED)
VALUES ('THRILLER', 'ADMIN');

----- email template


INSERT INTO public.email_templates
(subject, body)
VALUES('ERROR_FILE', '<h2 style="color: #e74c3c; text-align: center;">Error Processing File</h2>
    <p style="color: #555; font-size: 16px; text-align: center;">
        We''re sorry, but there was an issue while processing your file. Please try again or contact support if the problem persists.
    </p>
    <p style="font-size: 16px; text-align: center;">
        <strong>Error Details:</strong> <span th:text="${errorDetails}">Error details go here...</span>
    </p>');

INSERT INTO public.email_templates
(subject, body)
VALUES('FILE_CORRECT', '  <h2 style="color: #2ecc71; text-align: center;">File Processed Successfully</h2>
    <p style="color: #555; font-size: 16px; text-align: center;">
        Your file has been successfully processed. You can now proceed with the next steps.
    </p>');

INSERT INTO public.email_templates
(subject, body)
VALUES('MEMBERSHIP', '<div class="content">
            <h2>Congratulations, <span>:user</span>!</h2>
            <p>Your library membership has been successfully upgraded to <strong>:membership</strong>.</p>
            <p>Enjoy exclusive benefits, including extended borrowing periods, priority reservations, and access to premium collections.</p>
        </div>');


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

INSERT INTO public.email_templates
(subject, body)
VALUES('BOOK_LOAN', '<div>
    <h2>Books Loaded Successfully!</h2>
    <p>Dear User,</p>
    <p>Your books have been successfully loaded into the library system. Below is a list of the books along with their start and end dates:</p>

    <table style="width: 100%; border-collapse: collapse; margin-top: 20px; border: 1px solid #ddd;">
        <thead>
            <tr style="background-color: #f2f2f2;">
                <th style="border: 1px solid #ddd; padding: 8px; text-align: left;">ISBN</th>
                <th style="border: 1px solid #ddd; padding: 8px; text-align: left;">Book Name</th>
                <th style="border: 1px solid #ddd; padding: 8px; text-align: left;">Start Date</th>
                <th style="border: 1px solid #ddd; padding: 8px; text-align: left;">End Date</th>
            </tr>
        </thead>
        <tbody>
            <!-- BookListPlaceholder -->
        </tbody>
    </table>

    <p>If you have any questions, feel free to contact our support team.</p>
</div>';


INSERT INTO public.email_templates
(subject, body)
VALUES( 'UNBLOCK_USER', '<div class="content" style="margin-bottom: 20px; line-height: 1.6;">
    <h2 style="font-size: 20px; color: #4A90E2; margin-top: 0;">Verification code to unblock user</h2>
    <p style="margin: 10px 0;">Hello, <span>:name</span>!</p>
    <p style="margin: 10px 0;">We received a request to verify your account for the Library App. Use the code below to complete the verification process:</p>
    <div class="verification-code" style="background-color: #f0f0f0; padding: 10px; border-radius: 8px; margin: 20px 0; font-size: 16px; text-align: center;">
        <strong style="font-size: 18px; color: #4A90E2;">:verification_code</strong>
    </div>
    <p style="margin: 10px 0;">Please enter this code in the app to confirm your account. This code is valid for 10 minutes.</p>
    <p style="margin: 10px 0;">If you did not request this code, you can safely ignore this message.</p>
    <p style="margin: 10px 0;">If you have any questions or need support, feel free to reach out to us at
       <a href="mailto:support@yourlibraryapp.com" style="color: #4A90E2;">support@yourlibraryapp.com</a>.
    </p>
    <p style="margin: 10px 0;">Thank you for being a part of our community!</p>
</div>');