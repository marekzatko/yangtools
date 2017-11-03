/*
 * Copyright (c) 2015 Pantheon Technologies s.r.o. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.model.util.type;

import java.math.BigInteger;
import org.opendaylight.yangtools.yang.model.api.type.Uint64TypeDefinition;
import org.opendaylight.yangtools.yang.model.util.BaseTypes;

final class BaseUint64Type extends AbstractRangeRestrictedBaseType<Uint64TypeDefinition, BigInteger>
        implements Uint64TypeDefinition {
    static final BaseUint64Type INSTANCE = new BaseUint64Type();

    private BaseUint64Type() {
        super(BaseTypes.UINT64_QNAME, BigInteger.ZERO, new BigInteger("18446744073709551615"));
    }

    @Override
    public int hashCode() {
        return TypeDefinitions.hashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return TypeDefinitions.equals(this, obj);
    }

    @Override
    public String toString() {
        return TypeDefinitions.toString(this);
    }
}
