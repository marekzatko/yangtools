/*
 * Copyright (c) 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.parser.rfc7950.stmt.notification;

import com.google.common.collect.ImmutableList;
import org.eclipse.jdt.annotation.Nullable;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.model.api.DataSchemaNode;
import org.opendaylight.yangtools.yang.model.api.NotificationDefinition;
import org.opendaylight.yangtools.yang.model.api.SchemaNodeDefaults;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
import org.opendaylight.yangtools.yang.model.api.meta.EffectiveStatement;
import org.opendaylight.yangtools.yang.model.api.stmt.NotificationEffectiveStatement;
import org.opendaylight.yangtools.yang.model.api.stmt.NotificationStatement;
import org.opendaylight.yangtools.yang.parser.rfc7950.stmt.AbstractDeclaredEffectiveStatement.DefaultWithDataTree.WithSubstatements;
import org.opendaylight.yangtools.yang.parser.rfc7950.stmt.EffectiveStatementMixins.AugmentationTargetMixin;
import org.opendaylight.yangtools.yang.parser.rfc7950.stmt.EffectiveStatementMixins.CopyableMixin;
import org.opendaylight.yangtools.yang.parser.rfc7950.stmt.EffectiveStatementMixins.DataNodeContainerMixin;
import org.opendaylight.yangtools.yang.parser.rfc7950.stmt.EffectiveStatementMixins.MustConstraintMixin;
import org.opendaylight.yangtools.yang.parser.rfc7950.stmt.EffectiveStatementMixins.SchemaNodeMixin;

final class NotificationEffectiveStatementImpl
        extends WithSubstatements<QName, NotificationStatement, NotificationEffectiveStatement>
        implements NotificationDefinition, NotificationEffectiveStatement,
                   SchemaNodeMixin<QName, NotificationStatement>, DataNodeContainerMixin<QName, NotificationStatement>,
                   AugmentationTargetMixin<QName, NotificationStatement>, CopyableMixin<QName, NotificationStatement>,
                   MustConstraintMixin<QName, NotificationStatement> {

    private final @Nullable SchemaPath path;
    private final int flags;

    NotificationEffectiveStatementImpl(final NotificationStatement declared,
            final ImmutableList<? extends EffectiveStatement<?, ?>> substatements, final int flags,
            final SchemaPath path) {
        super(declared, substatements);
        this.path = path;
        this.flags = flags;
    }

    @Override
    public QName argument() {
        return getQName();
    }

    @Override
    public DataSchemaNode dataChildByName(final QName name) {
        return dataSchemaNode(name);
    }

    @Override
    public int flags() {
        return flags;
    }

    @Override
    @Deprecated
    public SchemaPath getPath() {
        return SchemaNodeDefaults.throwUnsupportedIfNull(this, path);
    }

    @Override
    public NotificationEffectiveStatement asEffectiveStatement() {
        return this;
    }

    @Override
    public String toString() {
        return NotificationEffectiveStatementImpl.class.getSimpleName() + "[qname=" + getQName() + ", path=" + path
                + "]";
    }
}
