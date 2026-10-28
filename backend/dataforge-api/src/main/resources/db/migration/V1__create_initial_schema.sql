
-- V1__create_initial_schema.sql
-- Schema inicial da plataforma DataForge.

-- =====================================================
-- 1. Tabelas principais
-- =====================================================

CREATE TABLE users (
    id UUID PRIMARY KEY,
    user_name VARCHAR(255),
    user_email VARCHAR(255),
    activate BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE pipelines (
    id UUID PRIMARY KEY,
    pipe_name VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE roles (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE permissions (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

-- =====================================================
-- 2. Tabelas de associação
-- =====================================================

CREATE TABLE user_pipelines (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    pipeline_id UUID NOT NULL,

    CONSTRAINT fk_user_pipelines_user
        FOREIGN KEY (user_id)
        REFERENCES users (id),

    CONSTRAINT fk_user_pipelines_pipeline
        FOREIGN KEY (pipeline_id)
        REFERENCES pipelines (id),

    CONSTRAINT uk_user_pipeline
        UNIQUE (user_id, pipeline_id)
);

CREATE TABLE user_roles (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
        REFERENCES users (id),

    CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id)
        REFERENCES roles (id),

    CONSTRAINT uk_user_role
        UNIQUE (user_id, role_id)
);

CREATE TABLE role_permissions (
    id UUID PRIMARY KEY,
    role_id UUID NOT NULL,
    permission_id UUID NOT NULL,

    CONSTRAINT fk_role_permissions_role
        FOREIGN KEY (role_id)
        REFERENCES roles (id),

    CONSTRAINT fk_role_permissions_permission
        FOREIGN KEY (permission_id)
        REFERENCES permissions (id),

    CONSTRAINT uk_role_permission
        UNIQUE (role_id, permission_id)
);

-- =====================================================
-- 3. Índices para consultas e relacionamentos
-- =====================================================

CREATE INDEX idx_user_pipelines_pipeline_id
    ON user_pipelines (pipeline_id);

CREATE INDEX idx_user_roles_role_id
    ON user_roles (role_id);

CREATE INDEX idx_role_permissions_permission_id
    ON role_permissions (permission_id);
