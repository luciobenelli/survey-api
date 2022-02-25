CREATE SEQUENCE SEQ_RESPONSE_ID START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

CREATE TABLE RESPONSE(ID NUMBER(38) NOT NULL,
                      RESPONDENT_ID NUMBER(38) NOT NULL,
                      SURVEY_ID NUMBER(38) NOT NULL,
                      CONSTRAINT PK_RESPONSE PRIMARY KEY (ID),
                      CONSTRAINT FK_RESPONSE_RESPONDENT_ID FOREIGN KEY(RESPONDENT_ID) REFERENCES RESPONSE(ID),
                      CONSTRAINT FK_RESPONSE_SURVEY_ID FOREIGN KEY(SURVEY_ID) REFERENCES SURVEY(ID)
                   );

COMMENT ON TABLE RESPONSE IS 'Contains the RESPONSE information.';

COMMENT ON COLUMN RESPONSE.ID IS 'Unique table identifier. Incremented using SEQ_RESPONSE_ID';
COMMENT ON COLUMN RESPONSE.RESPONDENT_ID IS 'Foreign key to RESPONDENT.ID';
