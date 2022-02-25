CREATE SEQUENCE SEQ_CHOICE_ID START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

CREATE TABLE CHOICE(ID NUMBER(38) NOT NULL,
                    DESCRIPTION VARCHAR2(2000) NOT NULL,
                    QUESTION_ID NUMBER(38) NOT NULL,
                    CONSTRAINT PK_CHOICE PRIMARY KEY (ID),
                    CONSTRAINT FK_CHOICE_QUESTION_ID FOREIGN KEY(QUESTION_ID) REFERENCES QUESTION(ID)
                   );

COMMENT ON TABLE CHOICE IS 'Contains the choice information.';

COMMENT ON COLUMN CHOICE.ID IS 'Unique table identifier. Incremented using SEQ_CHOICE_ID';
COMMENT ON COLUMN CHOICE.DESCRIPTION IS 'Choice description';
COMMENT ON COLUMN CHOICE.QUESTION_ID IS 'Foreign key to QUESTION.ID';
