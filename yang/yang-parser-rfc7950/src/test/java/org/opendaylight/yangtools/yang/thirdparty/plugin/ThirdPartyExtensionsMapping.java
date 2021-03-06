/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.thirdparty.plugin;

import static java.util.Objects.requireNonNull;

import com.google.common.annotations.Beta;
import java.util.Optional;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.model.api.meta.ArgumentDefinition;
import org.opendaylight.yangtools.yang.model.api.meta.DeclaredStatement;
import org.opendaylight.yangtools.yang.model.api.meta.EffectiveStatement;
import org.opendaylight.yangtools.yang.model.api.meta.StatementDefinition;

@Beta
public enum ThirdPartyExtensionsMapping implements StatementDefinition {
    THIRD_PARTY_EXTENSION("urn:opendaylight:yang:extension:third-party", "2016-06-09",
            ThirdPartyExtensionStatementImpl.class, ThirdPartyExtensionEffectiveStatementImpl.class,
            "third-party-extension", "argument-name", false);

    private final @NonNull Class<? extends DeclaredStatement<?>> type;
    private final @NonNull Class<? extends EffectiveStatement<?, ?>> effectiveType;
    private final @NonNull QName name;
    private final QName argument;
    private final boolean yinElement;

    ThirdPartyExtensionsMapping(final String namespace, final String revision,
            final Class<? extends DeclaredStatement<?>> declared,
            final Class<? extends EffectiveStatement<?, ?>> effective, final String nameStr, final String argumentStr,
            final boolean yinElement) {
        type = requireNonNull(declared);
        effectiveType = requireNonNull(effective);
        name = createQName(namespace, revision, nameStr);
        argument = createQName(namespace, revision, argumentStr);
        this.yinElement = yinElement;
    }

    ThirdPartyExtensionsMapping(final String namespace, final Class<? extends DeclaredStatement<?>> declared,
            final Class<? extends EffectiveStatement<?, ?>> effective, final String nameStr, final String argumentStr,
            final boolean yinElement) {
        type = requireNonNull(declared);
        effectiveType = requireNonNull(effective);
        name = createQName(namespace, nameStr);
        argument = createQName(namespace, argumentStr);
        this.yinElement = yinElement;
    }

    private static @NonNull QName createQName(final String namespace, final String localName) {
        return QName.create(namespace, localName).intern();
    }

    private static @NonNull QName createQName(final String namespace, final String revision, final String localName) {
        return QName.create(namespace, revision, localName).intern();
    }

    @Override
    public QName getStatementName() {
        return name;
    }

    @Override
    public @NonNull Optional<ArgumentDefinition> getArgumentDefinition() {
        return ArgumentDefinition.ofNullable(argument, yinElement);
    }

    @Override
    public Class<? extends DeclaredStatement<?>> getDeclaredRepresentationClass() {
        return type;
    }

    @Override
    public Class<? extends EffectiveStatement<?, ?>> getEffectiveRepresentationClass() {
        return effectiveType;
    }
}
